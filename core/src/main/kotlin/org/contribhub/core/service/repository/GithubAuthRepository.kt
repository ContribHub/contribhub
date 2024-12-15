package org.contribhub.core.service.repository

import org.contribhub.core.service.dto.UserInfo

interface GithubAuthRepository {
    suspend fun getAuthenticatedUser(accessToken: String): UserInfo
}
