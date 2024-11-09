package org.contribhub.api.service

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.contribhub.api.infra.http.GithubClient
import org.contribhub.api.infra.http.dto.GithubCodeToAccessTokenResponse
import org.contribhub.api.infra.http.dto.GithubRepositoryResponse
import org.contribhub.api.infra.repository.RepositoryJpaRepository
import org.contribhub.api.infra.repository.TopicJpaRepository
import org.contribhub.api.infra.repository.entity.RepositoryEntity
import org.contribhub.api.infra.repository.entity.TopicEntity
import org.springframework.stereotype.Service

@Service
class GithubService(
    private val githubClient: GithubClient,
    private val topicJpaRepository: TopicJpaRepository,
    private val repositoryJpaRepository: RepositoryJpaRepository,
) {
    suspend fun searchRepositories(
        query: String,
        sort: String?,
        order: String?,
        perPage: Int?,
        page: Int?,
    ): GithubRepositoryResponse = githubClient.searchRepositories(query = query, sort = sort, order = order, perPage = perPage, page = page)

    suspend fun fetchLatestRepositoriesByTopics(topics: List<TopicEntity>): List<RepositoryEntity> =
        coroutineScope {
            topics
                .map { topic ->
                    async {
                        fetchLatestRepositoriesByTopic(topic)
                    }
                }.awaitAll()
                .flatMap(GithubRepositoryResponse::toRepositoryEntities)
        }

    private suspend fun fetchLatestRepositoriesByTopic(topic: TopicEntity) =
        this.searchRepositories(
            query = "$${topic.name}+is:featured",
            sort = "stars",
            order = "desc",
            perPage = 30,
            page = 1,
        )

    // TODO 일단은 같이 두었는데 별도의 서비스로 분리 가능
    fun getTopics() = topicJpaRepository.getTopics()

    fun upsertRepositories(repositories: List<RepositoryEntity>) {
        repositoryJpaRepository.upsertRepositories(repositories)
    }

    suspend fun accessToken(code: String): GithubCodeToAccessTokenResponse = githubClient.codeToAccessToken(code)
}
