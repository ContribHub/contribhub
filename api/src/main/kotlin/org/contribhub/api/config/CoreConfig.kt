package org.contribhub.api.config

import org.contribhub.core.service.RepositoryService
import org.contribhub.core.service.SearchKeywordService
import org.contribhub.core.service.UserAuthService
import org.contribhub.infra.adapter.GithubAuthAdapter
import org.contribhub.infra.adapter.LanguageAdapter
import org.contribhub.infra.adapter.LicenseAdapter
import org.contribhub.infra.adapter.RepositoryAdapter
import org.contribhub.infra.adapter.TopicAdapter
import org.contribhub.infra.http.GithubClient
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
        licenseAdapter: LicenseAdapter,
    ): SearchKeywordService = SearchKeywordService(languageAdapter, licenseAdapter, topicAdapter)

    @Bean
    fun repositoryService(repositoryAdapter: RepositoryAdapter): RepositoryService = RepositoryService(repositoryAdapter)
}
