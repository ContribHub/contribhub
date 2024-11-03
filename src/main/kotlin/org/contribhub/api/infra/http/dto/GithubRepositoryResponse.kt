package org.contribhub.api.infra.http.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.contribhub.api.infra.repository.entity.RepositoryEntity
import java.time.Instant

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class GithubRepositoryResponse(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<Repository>,
) {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Repository(
        val id: Long,
        val name: String,
        val description: String?,
        val fullName: String,
        val private: Boolean,
        val owner: Owner,
        val forkCount: Int,
        val openIssueCount: Int,
        val htmlUrl: String,
        val stargazersCount: Int,
        val createdAt: Instant,
        val updatedAt: Instant,
        val license: License?,
        val language: String?,
    )

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Owner(
        val id: Long,
        val login: String,
    )

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class License(
        val key: String,
        val name: String,
        val spdxId: String?,
    )

    fun toRepositoryEntities(): List<RepositoryEntity> =
        this.items.map {
            RepositoryEntity(
                repoId = it.id,
                repoName = it.name,
                repoFullName = it.fullName,
                repoDescription = it.description,
                ownerName = it.owner.login,
                ownerId = it.owner.id,
                forkCount = it.forkCount,
                openIssueCount = it.openIssueCount,
                mainUrl = it.htmlUrl,
                starCount = it.stargazersCount,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt,
                licenSeq = null,
                languageSeq = null,
            )
        }
}
