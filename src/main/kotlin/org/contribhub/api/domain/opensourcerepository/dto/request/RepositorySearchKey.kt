package org.contribhub.api.domain.opensourcerepository.dto.request

data class RepositorySearchKey(
    val licenId: Long?,
    val topicId: Long?,
    val languageId: Long?,
    val repoName: String?,
)
