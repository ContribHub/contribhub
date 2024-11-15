package org.contribhub.api.domain.opensourcerepository.repository.repositories

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import jakarta.persistence.EntityManagerFactory
import org.contribhub.api.infra.repository.entity.RepositoryEntity

class CustomRepositoryEntityRepositoryImpl(
    private val jpqlExecutor: KotlinJdslJpqlExecutor,
    private val entityManagerFactory: EntityManagerFactory,
) : CustomRepositoryEntityRepository {
    override fun upsertRepositories(repositories: List<RepositoryEntity>) {
        // TODO : 현재 id 생성전략이 IDENTITY라서 배치사용 불가 - id 생성전략 변경 필요.
        val entityManager = entityManagerFactory.createEntityManager()
        val transaction = entityManager.transaction
        transaction.begin() // 트랜잭션 시작

        repositories.forEachIndexed { index, data ->
            val query =
                entityManager.createNativeQuery(
                    """
                    INSERT INTO REPOSITORIES (
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
                        updated_dt)
                    VALUES (
                        :repoId,
                        :repoName,
                        :repoFullName,
                        :repoDescription,
                        :ownerName,
                        :ownerId,
                        :forkCount,
                        :openIssueCount,
                        :mainUrl,
                        :starCount,
                        now()
                    )
                    on conflict(repo_id)
                    DO UPDATE SET
                        repo_id = EXCLUDED.repo_id, 
                        repo_name = EXCLUDED.repo_name, 
                        repo_full_name = EXCLUDED.repo_full_name, 
                        repo_description = EXCLUDED.repo_description, 
                        owner_Name = EXCLUDED.owner_Name,
                        owner_id = EXCLUDED.owner_id,
                        fork_count = EXCLUDED.fork_count,
                        open_issue_count = EXCLUDED.open_issue_count,
                        main_url = EXCLUDED.main_url,
                        star_count = EXCLUDED.star_count,
                        updated_dt = EXCLUDED.updated_dt
                    """.trimIndent(),
                )

            query.setParameter("repoId", data.repoId)
            query.setParameter("repoName", data.repoName)
            query.setParameter("repoFullName", data.repoFullName)
            query.setParameter("repoDescription", data.repoDescription)
            query.setParameter("ownerName", data.ownerName)
            query.setParameter("ownerId", data.ownerId)
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
