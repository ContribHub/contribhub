package org.contribhub.api.config

import org.contribhub.api.infra.adapter.GithubAuthAdapter
import org.contribhub.api.infra.http.GithubClient
import org.contribhub.core.service.service.UserAuthService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CoreConfig {
    @Bean
    fun githubAuthService(githubClient: GithubClient): UserAuthService = UserAuthService(GithubAuthAdapter(githubClient))
}
