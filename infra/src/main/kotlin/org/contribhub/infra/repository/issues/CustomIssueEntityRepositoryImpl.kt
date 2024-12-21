package org.contribhub.infra.repository.issues

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.contribhub.infra.repository.entity.IssueEntity
import org.contribhub.infra.repository.entity.RepositoryEntity
import org.springframework.data.domain.Pageable

class CustomIssueEntityRepositoryImpl(
    private val jpqlExecutor: KotlinJdslJpqlExecutor,
) : CustomIssueEntityRepository {
    override fun findIssueListPage(
        repoId: Long,
        lastId: Long,
        pageable: Pageable,
    ): List<IssueEntity> =
        jpqlExecutor
            .findPage(pageable) {
                select<IssueEntity>(
                    entity(IssueEntity::class),
                ).from(
                    entity(IssueEntity::class),
                    join(IssueEntity::repositoryEntity),
                ).where(
                    and(
                        path(IssueEntity::repositoryEntity).path(RepositoryEntity::repoSeq).eq(repoId),
                        path(IssueEntity::issueSeq).gt(lastId),
                    ),
                ).orderBy(
                    path(IssueEntity::issueSeq).asc(),
                )
            }.filterNotNull()
}
