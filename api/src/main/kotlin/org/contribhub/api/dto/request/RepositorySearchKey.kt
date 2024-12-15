package org.contribhub.api.dto.request

data class RepositorySearchKey(
    val licenId: Long?,
    val topicId: Long?,
    val languageId: Long?,
    val repoName: String?,
)
