package org.contribhub.core.service

import org.contribhub.core.entity.UserInfo
import org.contribhub.core.repository.GithubAuthRepository

class UserAuthService(
    private val githubAuthRepository: GithubAuthRepository,
) {
    suspend fun getAuthenticatedUser(accessToken: String): UserInfo = githubAuthRepository.getAuthenticatedUser(accessToken)
}
