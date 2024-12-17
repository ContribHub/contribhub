package org.contribhub.api.dto.response

import org.contribhub.core.entity.Repository

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
    companion object {
        fun from(repository: Repository): RepositoryListResponse =
            RepositoryListResponse(
                repoId = repository.repoId,
                repoName = repository.repoName,
                repoOwnerId = repository.repoOwnerId,
                repoOwnerName = repository.repoOwnerName,
                openIssueCount = repository.openIssueCount,
                repoUrl = repository.repoUrl,
                viewCount = repository.viewCount,
                starCount = repository.starCount,
                licenseName = repository.licenseName,
                mainLanguage = repository.mainLanguage,
                forkCount = repository.forkCount,
            )
    }
}
