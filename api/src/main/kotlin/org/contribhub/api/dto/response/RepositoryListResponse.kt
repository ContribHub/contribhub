package org.contribhub.api.dto.response

import org.contribhub.core.service.entity.Repository

data class RepositoryListResponse(
    // contribhub의 레포지토리 테이블 시퀀스
    val repoId: Long,
    val repoName: String,
    val repoOwnerId: String,
    val repoOwnerName: String,
    val openIssueCount: Int,
    val repoUrl: String,
    val viewCount: Int,
    val starCount: Int,
    val licenseName: String,
    val mainLanguage: String,
    val forkCount: Int,
) {
    fun toDomain(): Repository =
        Repository(
            repoId = repoId,
            repoName = repoName,
            repoOwnerId = repoOwnerId,
            repoOwnerName = repoOwnerName,
            openIssueCount = openIssueCount,
            repoUrl = repoUrl,
            viewCount = viewCount,
            starCount = starCount,
            licenseName = licenseName,
            mainLanguage = mainLanguage,
            forkCount = forkCount,
        )
}
