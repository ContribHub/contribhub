package org.contribhub.infra.http.dto

data class GithubGetAuthenticatedUserResponse(
    val id: Long,
    val login: String,
)
