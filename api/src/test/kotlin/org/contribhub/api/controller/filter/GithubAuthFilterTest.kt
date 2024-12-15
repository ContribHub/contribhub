package org.contribhub.api.controller.filter

import org.contribhub.api.config.TestConfig
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.HttpHeaders
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@AutoConfigureWebTestClient
@Testcontainers
@Import(TestConfig::class)
class GithubAuthFilterTest(
    private val webTestClient: WebTestClient,
) {
    @ParameterizedTest
    @ValueSource(strings = ["Bearer InValidToken", "InValidToken"])
    @EmptySource
    fun `인증 실패 시 StatusCode 403 응답`(authorization: String) {
        webTestClient
            .get()
            .uri("/github/auth-test")
            .header(HttpHeaders.AUTHORIZATION, authorization)
            .exchange()
            .expectStatus()
            .isUnauthorized
    }
}
