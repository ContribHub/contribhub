package org.contribhub.api.infra.adapter

import org.contribhub.api.infra.http.GithubClient
import org.contribhub.core.service.dto.UserInfo
import org.contribhub.core.service.repository.GithubAuthRepository

class GithubAuthAdapter(
    private val githubClient: GithubClient,
) : GithubAuthRepository {
    override suspend fun getAuthenticatedUser(accessToken: String): UserInfo {
        val response = githubClient.getAuthenticatedUser(accessToken)
        return UserInfo(id = response.id, nickname = response.login)
    }
}
