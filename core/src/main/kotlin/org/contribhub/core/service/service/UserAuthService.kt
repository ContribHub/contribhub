package org.contribhub.core.service.service

import org.contribhub.core.service.entity.UserInfo
import org.contribhub.core.service.repository.GithubAuthRepository

class UserAuthService(
    private val githubAuthRepository: GithubAuthRepository,
) {
    suspend fun getAuthenticatedUser(accessToken: String): UserInfo = githubAuthRepository.getAuthenticatedUser(accessToken)
}
