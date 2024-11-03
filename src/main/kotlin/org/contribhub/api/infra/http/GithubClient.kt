package org.contribhub.api.infra.http

import org.contribhub.api.infra.http.dto.GithubRepositoryResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class GithubClient(
    private val webClient: WebClient,
    @Value("\${api.github.auth-token}") private val apiToken: String,
) {
    private val host = "https://api.github.com"

    /**
     * @see <a href = "https://docs.github.com/en/rest/search/search?apiVersion=2022-11-28#search-repositories">Search repositories API Spec</a>
     * @see <a href = "https://docs.github.com/ko/search-github/searching-on-github/searching-for-repositories">Search Qualifiers</a>
     * @query query: The search keywords, as well as any qualifiers.
     */
    suspend fun searchRepositories(
        query: String,
        sort: String? = "stars", // stars, forks, help-wanted-issues, updated
        order: String? = "desc",
        perPage: Int? = 30, // max : 100
        page: Int? = 1,
    ): GithubRepositoryResponse =
        webClient
            .get()
            .uri("$host/search/repositories") { uriBuilder ->
                uriBuilder
                    .queryParam("q", query)
                    .queryParam("sort", sort)
                    .queryParam("order", order)
                    .queryParam("per_page", perPage)
                    .queryParam("page", page)
                    .build()
            }.headers {
                it.add(HttpHeaders.AUTHORIZATION, "Bearer $apiToken")
            }.retrieve()
            .awaitBody()
}
