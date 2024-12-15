package org.contribhub.api.infra.http.dto

data class GithubGetAuthenticatedUserResponse(
    val id: Long,
    val login: String,
)
