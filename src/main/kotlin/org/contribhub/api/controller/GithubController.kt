package org.contribhub.api.controller

import com.fasterxml.jackson.databind.JsonNode
import org.contribhub.api.http.GithubClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/github")
@RestController
class GithubController(
    private val githubClient: GithubClient,
) {
    @GetMapping("/search/repositories")
    suspend fun searchRepositories(
        @RequestParam(name = "query") query: String,
        @RequestParam sort: String?,
        @RequestParam order: String?,
        @RequestParam perPage: Int?,
        @RequestParam page: Int?,
    ): JsonNode = githubClient.searchRepositories(query = query, sort = sort, order = order, perPage = perPage, page = page)
}
