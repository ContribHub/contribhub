package org.contribhub.core.entity

// todo domain에 있기에는 살짝 어색하다는 느낌
data class RepositorySearchKey(
    val licenId: Long?,
    val topicId: Long?,
    val languageId: Long?,
    val repoName: String?,
)
