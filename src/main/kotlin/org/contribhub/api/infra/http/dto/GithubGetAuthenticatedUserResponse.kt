package org.contribhub.api.infra.http.dto

import org.contribhub.api.service.dto.UserInfo

data class GithubGetAuthenticatedUserResponse(
    val id: Long,
    val login: String,
) {
    fun toUserInfo() =
        UserInfo(
            id = id,
            nickname = login,
        )
}
