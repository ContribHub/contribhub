package org.contribhub.infra.repository.repositories.nativequery

import jakarta.persistence.EntityManagerFactory
import org.contribhub.infra.repository.entity.GithubRepositoryEntity

class CustomRepositoryEntityNativeRepositoryImpl(
    private val entityManagerFactory: EntityManagerFactory,
) : CustomRepositoryEntityNativeRepository {
    override fun upsertRepositories(repositories: List<GithubRepositoryEntity>) {
        // TODO : 현재 id 생성전략이 IDENTITY라서 배치사용 불가 - id 생성전략 변경 필요.
        val entityManager = entityManagerFactory.createEntityManager()
        val transaction = entityManager.transaction
        transaction.begin() // 트랜잭션 시작

        repositories.forEachIndexed { index, data ->
            val query =
                entityManager.createNativeQuery(
                    """
                    MERGE INTO REPOSITORIES
                    USING(
                        SELECT
                            :repoId as repo_id,
                            :repoName as repo_name,
                            :repoFullName as repo_full_name,
                            :repoDescription as repo_description,
                            :ownerName as owner_name,
                            :ownerId as owner_id,
                            :forkCount as fork_count,
                            :openIssueCount as open_issue_count,
                            :mainUrl as main_url,
                            :starCount as star_count
                    ) as input_source
                    ON REPOSITORIES.repo_id = input_source.repo_id
                    WHEN MATCHED THEN
                        UPDATE SET
                            repo_id = input_source.repo_id, 
                            repo_name = input_source.repo_name, 
                            repo_full_name = input_source.repo_full_name, 
                            repo_description = input_source.repo_description, 
                            owner_Name = input_source.owner_Name,
                            owner_id = input_source.owner_id,
                            fork_count = input_source.fork_count,
                            open_issue_count = input_source.open_issue_count,
                            main_url = input_source.main_url,
                            star_count = input_source.star_count,
                            updated_dt = now()
                    WHEN NOT MATCHED THEN
                        INSERT (
                            repo_id, 
                            repo_name, 
                            repo_full_name, 
                            repo_description, 
                            owner_Name,
                            owner_id,
                            fork_count,
                            open_issue_count,
                            main_url,
                            star_count,
                            updated_dt
                        ) VALUES (
                            input_source.repo_id,
                            input_source.repo_name,
                            input_source.repo_full_name,
                            input_source.repo_description,
                            input_source.owner_name,
                            input_source.owner_id,
                            input_source.fork_count,
                            input_source.open_issue_count,
                            input_source.main_url,
                            input_source.star_count,
                            now()
                        )
                    """.trimIndent(),
                )

            query.setParameter("repoId", data.repoId.toString())
            query.setParameter("repoName", data.repoName)
            query.setParameter("repoFullName", data.repoFullName)
            query.setParameter("repoDescription", data.repoDescription)
            query.setParameter("ownerName", data.ownerName)
            query.setParameter("ownerId", data.ownerId.toString())
            query.setParameter("forkCount", data.forkCount)
            query.setParameter("openIssueCount", data.openIssueCount)
            query.setParameter("mainUrl", data.mainUrl)
            query.setParameter("starCount", data.starCount)
            query.executeUpdate()

            // 건수가 많을 수 있으니 50건 단위로 처리 - 건수 변경시에는 yml의 배치 사이즈에 맞게 변경해야 함.
            if (index % 500 != 0) return@forEachIndexed

            entityManager.flush()
            entityManager.clear()
        }

        transaction.commit(); // 트랜잭션 커밋
    }
}
