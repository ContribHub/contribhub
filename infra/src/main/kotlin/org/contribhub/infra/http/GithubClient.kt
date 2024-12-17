package org.contribhub.infra.http

import org.contribhub.infra.http.dto.GithubCodeToAccessRequest
import org.contribhub.infra.http.dto.GithubCodeToAccessTokenResponse
import org.contribhub.infra.http.dto.GithubGetAuthenticatedUserResponse
import org.contribhub.infra.http.dto.GithubRepositoryResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class GithubClient(
    private val webClient: WebClient,
    @Value("\${api.github.auth-token}") private val apiToken: String,
    @Value("\${api.github.client-id}") private val clientId: String,
    @Value("\${api.github.client-secret}") private val clientSecret: String,
) {
    companion object {
        private const val GITHUB_API_HOST = "https://api.github.com"

        private const val GITHUB_HOST = "https://github.com"
    }

    /**
     * @see <a href = "https://docs.github.com/en/rest/search/search?apiVersion=2022-11-28#search-repositories">Search repositories API Spec</a>
     * @see <a href = "https://docs.github.com/ko/search-github/searching-on-github/searching-for-repositories">Search Qualifiers</a>
     * @query query: The search keywords, as well as any qualifiers.
     */
    suspend fun searchRepositories(
        query: String,
        // stars, forks, help-wanted-issues, updated
        sort: String? = "stars",
        order: String? = "desc",
        // max : 100
        perPage: Int? = 30,
        page: Int? = 1,
    ): GithubRepositoryResponse =
        webClient
            .get()
            .uri("$GITHUB_API_HOST/search/repositories") { uriBuilder ->
                uriBuilder
                    .queryParam("q", query)
                    .queryParam("sort", sort)
                    .queryParam("order", order)
                    .queryParam("per_page", perPage)
                    .queryParam("page", page)
                    .build()
            }.header(HttpHeaders.AUTHORIZATION, "Bearer $apiToken")
            .retrieve()
            .awaitBody()

    /**
     * @see<a href="https://docs.github.com/en/apps/oauth-apps/building-oauth-apps/authorizing-oauth-apps">Authorizing OAuth apps</a>
     */
    suspend fun resolveCodeToAccessToken(code: String): GithubCodeToAccessTokenResponse =
        webClient
            .post()
            .uri("$GITHUB_HOST/login/oauth/access_token")
            .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .body(
                BodyInserters.fromValue(
                    GithubCodeToAccessRequest(
                        clientId = clientId,
                        clientSecret = clientSecret,
                        code = code,
                    ),
                ),
            ).retrieve()
            .onStatus(HttpStatusCode::is4xxClientError) {
                throw IllegalArgumentException("Invalid Code ($code)")
            }.awaitBody()

    /**
     * @see <a href="https://docs.github.com/en/rest/users/users?apiVersion=2022-11-28#get-the-authenticated-user">Get the authenticated User</a>
     */
    suspend fun getAuthenticatedUser(accessToken: String): GithubGetAuthenticatedUserResponse =
        webClient
            .get()
            .uri("$GITHUB_API_HOST/user")
            .header(HttpHeaders.AUTHORIZATION, "Bearer $accessToken")
            .retrieve()
            .awaitBody()
}
