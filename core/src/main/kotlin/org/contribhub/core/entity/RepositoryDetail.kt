package org.contribhub.core.entity

data class RepositoryDetail(
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
)
