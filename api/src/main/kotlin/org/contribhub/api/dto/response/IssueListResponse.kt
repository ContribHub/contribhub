package org.contribhub.api.dto.response

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
)
