package org.contribhub.api.infra

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.contribhub.api.infra.http.GithubClient
import org.contribhub.api.infra.http.dto.GithubRepositoryResponse
import org.contribhub.api.infra.http.dto.TopicInfoDTO
import org.contribhub.api.infra.repository.RepositoryJpaRepository
import org.contribhub.api.infra.repository.entity.RepositoryEntity
import org.contribhub.api.repository.topics.TopicEntityRepository

class GithubCronBatch(
    private val githubClient: GithubClient,
    private val topicEntityRepository: TopicEntityRepository,
    private val repositoryJpaRepository: RepositoryJpaRepository,
) {
    // TODO Replica 추가 시 쿠버네티스 CronJob으로 변경 가능
    // 새벽 1시에 수행하는 Cron Job
    // @Scheduled(cron = "0 */1 * * * ?")
    suspend fun fetchAndUpsertRepositoriesByTopics() {
        val topics = getTopics()
        val repositories = fetchLatestRepositoriesByTopics(topics)
        upsertRepositories(repositories)
    }

    private suspend fun searchRepositories(
        query: String,
        sort: String?,
        order: String?,
        perPage: Int?,
        page: Int?,
    ): GithubRepositoryResponse = githubClient.searchRepositories(query = query, sort = sort, order = order, perPage = perPage, page = page)

    private suspend fun fetchLatestRepositoriesByTopics(topics: List<TopicInfoDTO>): List<RepositoryEntity> =
        coroutineScope {
            topics
                .map { topic ->
                    async {
                        fetchLatestRepositoriesByTopic(topic)
                    }
                }.awaitAll()
                .flatMap(GithubRepositoryResponse::toRepositoryEntities)
        }

    private suspend fun fetchLatestRepositoriesByTopic(topic: TopicInfoDTO) =
        this.searchRepositories(
            query = "$${topic.name}+is:featured",
            sort = "stars",
            order = "desc",
            perPage = 30,
            page = 1,
        )

    // TODO 일단은 같이 두었는데 별도의 서비스로 분리 가능
    private fun getTopics() = topicEntityRepository.getTopics()

    private fun upsertRepositories(repositories: List<RepositoryEntity>) {
        repositoryJpaRepository.upsertRepositories(repositories)
    }
}
