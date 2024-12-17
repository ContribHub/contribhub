package org.contribhub.core.repository

import org.contribhub.core.entity.UserInfo

interface GithubAuthRepository {
    suspend fun getAuthenticatedUser(accessToken: String): UserInfo
}
