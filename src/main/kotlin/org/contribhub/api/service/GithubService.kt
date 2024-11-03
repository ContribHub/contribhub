package org.contribhub.api.service

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.contribhub.api.infra.http.GithubClient
import org.contribhub.api.infra.http.dto.GithubRepositoryResponse
import org.contribhub.api.infra.repository.TopicRepository
import org.contribhub.api.infra.repository.entity.RepositoryEntity
import org.contribhub.api.infra.repository.entity.TopicEntity
import org.springframework.stereotype.Service

@Service
class GithubService(
    private val githubClient: GithubClient,
    private val topicRepository: TopicRepository,
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

    fun getTopics() = topicRepository.getTopics()

    // TODO
    fun upsertRepositories(repositories: List<RepositoryEntity>) {
    }
}
