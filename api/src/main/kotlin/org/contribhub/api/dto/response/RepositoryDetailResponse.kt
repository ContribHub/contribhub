package org.contribhub.api.dto.response

import org.contribhub.core.service.entity.RepositoryDetail

data class RepositoryDetailResponse(
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
    val repoFullName: String,
    val repoDescription: String,
    val forkCount: Int,
) {
    companion object {
        fun from(repositoryDetail: RepositoryDetail): RepositoryDetailResponse =
            with(repositoryDetail) {
                RepositoryDetailResponse(
                    repoId = this.repoId,
                    repoName = this.repoName,
                    repoOwnerId = this.repoOwnerId,
                    repoOwnerName = this.repoOwnerName,
                    openIssueCount = this.openIssueCount,
                    repoUrl = this.repoUrl,
                    viewCount = this.viewCount,
                    starCount = this.starCount,
                    licenseName = this.licenseName,
                    mainLanguage = this.mainLanguage,
                    repoFullName = this.repoFullName,
                    repoDescription = this.repoDescription,
                    forkCount = this.forkCount
                )
            }

    }
}