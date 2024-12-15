package org.contribhub.api.repository.issues

import org.contribhub.api.dto.response.IssueListResponse
import org.springframework.data.domain.Pageable

interface CustomIssueEntityRepository {
    // 이슈 목록조회.
    fun findIssueListPage(
        repoId: Long,
        lastId: Long,
        pageable: Pageable,
    ): List<IssueListResponse>
}
