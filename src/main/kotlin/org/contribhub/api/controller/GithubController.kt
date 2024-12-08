package org.contribhub.api.controller

import org.contribhub.api.infra.http.dto.GithubCodeToAccessTokenResponse
import org.contribhub.api.infra.http.dto.GithubRepositoryResponse
import org.contribhub.api.service.GithubService
import org.contribhub.api.service.dto.UserInfo
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/github")
@RestController
class GithubController(
    private val githubService: GithubService,
) {
    @GetMapping("/search/repositories")
    suspend fun searchRepositories(
        @RequestParam(name = "query", required = true) query: String,
        @RequestParam sort: String?,
        @RequestParam order: String?,
        @RequestParam(name = "per_page") perPage: Int?,
        @RequestParam page: Int?,
    ): GithubRepositoryResponse =
        githubService.searchRepositories(query = query, sort = sort, order = order, perPage = perPage, page = page)

    @GetMapping("/login/callback")
    suspend fun callback(
        @RequestParam code: String,
    ): GithubCodeToAccessTokenResponse = githubService.resolveCodeToAccessToken(code)

    @Deprecated("For Test Purpose Only")
    @GetMapping("/auth-test")
    fun authenticationTest(
        @AuthenticationPrincipal user: UserInfo,
    ): UserInfo = user
}
