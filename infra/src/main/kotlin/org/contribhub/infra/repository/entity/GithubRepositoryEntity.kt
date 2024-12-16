package org.contribhub.infra.repository.entity

import java.time.Instant

data class GithubRepositoryEntity(
    var repoSeq: Int? = null,
    val repoId: Long,
    val repoName: String,
    val repoFullName: String,
    val repoDescription: String?,
    val ownerName: String,
    val ownerId: Long,
    val forkCount: Int,
    val openIssueCount: Int,
    val mainUrl: String,
//    val viewCount: Int, // TODO 어떤 필드인지 논의 필요
    val starCount: Int,
    val createdAt: Instant,
    val updatedAt: Instant,
    // TODO 라이센스 없을 수 있음 논의 필요
    val licenSeq: Int?,
    // TODO 언어 없을 수 있음 논의 필요
    val languageSeq: Int?,
)
