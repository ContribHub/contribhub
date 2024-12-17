package org.contribhub.infra.repository.issues

import org.contribhub.infra.repository.entity.IssueEntity
import org.springframework.data.domain.Pageable

interface CustomIssueEntityRepository {
    // 이슈 목록조회.
    fun findIssueListPage(
        repoId: Long,
        lastId: Long,
        pageable: Pageable,
    ): List<IssueEntity>
}
