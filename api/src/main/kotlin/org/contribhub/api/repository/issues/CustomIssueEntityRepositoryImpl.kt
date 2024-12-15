package org.contribhub.api.repository.issues

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.contribhub.api.dto.response.IssueListResponse
import org.contribhub.api.entity.IssueEntity
import org.contribhub.api.entity.RepositoryEntity
import org.springframework.data.domain.Pageable

class CustomIssueEntityRepositoryImpl(
    private val jpqlExecutor: KotlinJdslJpqlExecutor,
) : CustomIssueEntityRepository {
    override fun findIssueListPage(
        repoId: Long,
        lastId: Long,
        pageable: Pageable,
    ): List<IssueListResponse> =
        jpqlExecutor
            .findPage(pageable) {
                selectNew<IssueListResponse>(
                    path(IssueEntity::issueSeq).`as`(expression(Long::class, "id")),
                    path(IssueEntity::issueId),
                    path(IssueEntity::issueTitle),
                    path(IssueEntity::issueUrl),
                    path(IssueEntity::issueOwnerId),
                    path(IssueEntity::issueOwnerName),
                    path(IssueEntity::openYn),
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
