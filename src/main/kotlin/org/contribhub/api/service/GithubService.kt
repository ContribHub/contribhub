package org.contribhub.api.service

import org.contribhub.api.infra.http.GithubClient
import org.springframework.stereotype.Service

@Service
class GithubService(
    private val githubClient: GithubClient,
) {
    suspend fun searchRepositories(
        query: String,
        sort: String?,
        order: String?,
        perPage: Int?,
        page: Int?,
    ) = githubClient.searchRepositories(query = query, sort = sort, order = order, perPage = perPage, page = page)
}
