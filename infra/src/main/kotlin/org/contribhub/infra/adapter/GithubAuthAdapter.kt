package org.contribhub.infra.adapter

import org.contribhub.core.service.entity.UserInfo
import org.contribhub.core.service.repository.GithubAuthRepository
import org.contribhub.infra.http.GithubClient
import org.springframework.stereotype.Component

@Component
class GithubAuthAdapter(
    private val githubClient: GithubClient,
) : GithubAuthRepository {
    override suspend fun getAuthenticatedUser(accessToken: String): UserInfo {
        val response = githubClient.getAuthenticatedUser(accessToken)
        return UserInfo(id = response.id, nickname = response.login)
    }
}
