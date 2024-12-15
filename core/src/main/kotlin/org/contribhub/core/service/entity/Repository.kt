package org.contribhub.core.service.entity

data class Repository(
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
)