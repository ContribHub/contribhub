package org.contribhub.infra.http.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class GithubCodeToAccessTokenResponse(
    val accessToken: String,
    val refreshToken: String,
    val tokenType: String,
)
