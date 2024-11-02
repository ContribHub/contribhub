package org.contribhub.api.infra.http

import com.fasterxml.jackson.databind.JsonNode
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

    suspend fun searchRepositories(
        query: String,
        sort: String? = "stars",
        order: String? = "desc",
        perPage: Int? = 10,
        page: Int? = 1,
    ): JsonNode =
        webClient
            .get()
            .uri("$host/search/repositories?q=$query?sort=$sort&order=$order&per_page=$perPage&page=$page")
            .headers {
                it.add(HttpHeaders.AUTHORIZATION, "Bearer $apiToken")
            }.retrieve()
            .awaitBody()
}
