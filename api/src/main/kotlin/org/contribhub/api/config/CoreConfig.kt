package org.contribhub.api.config

import org.contribhub.api.infra.adapter.GithubAuthAdapter
import org.contribhub.api.infra.adapter.LanguageAdapter
import org.contribhub.api.infra.adapter.LicenseAdapter
import org.contribhub.api.infra.adapter.TopicAdapter
import org.contribhub.api.infra.http.GithubClient
import org.contribhub.core.service.service.SearchKeywordService
import org.contribhub.core.service.service.UserAuthService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CoreConfig {
    @Bean
    fun githubAuthService(githubClient: GithubClient): UserAuthService = UserAuthService(GithubAuthAdapter(githubClient))

    @Bean
    fun searchKeywordService(
        languageAdapter: LanguageAdapter,
        topicAdapter: TopicAdapter,
        licenseAdapter: LicenseAdapter
    ): SearchKeywordService = SearchKeywordService(languageAdapter, licenseAdapter, topicAdapter)
}
