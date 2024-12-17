package org.contribhub.api.dto.response

import org.contribhub.core.entity.Issue

data class IssueListResponse(
    // 이슈테이블 식별자
    val id: Long,
    // 깃허브에서 이슈 식별하는 id값
    val issueId: String,
    val issueTitle: String,
    val issueUrl: String,
    val issueOwnerId: String,
    val issueOwnerName: String,
    val openYn: Boolean,
) {
    companion object {
        fun from(issue: Issue): IssueListResponse =
            IssueListResponse(
                id = issue.id,
                issueId = issue.issueId,
                issueTitle = issue.issueTitle,
                issueUrl = issue.issueUrl,
                issueOwnerId = issue.issueOwnerId,
                issueOwnerName = issue.issueOwnerName,
                openYn = issue.openYn,
            )
    }
}
