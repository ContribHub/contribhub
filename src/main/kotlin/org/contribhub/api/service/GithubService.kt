package org.contribhub.api.service

import org.contribhub.api.infra.http.GithubClient
import org.contribhub.api.infra.http.dto.GithubRepositoryResponse
import org.contribhub.api.infra.repository.TopicRepository
import org.contribhub.api.infra.repository.entity.RepositoryEntity
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

    fun getTopics() = topicRepository.getTopics()

    // TODO
    fun upsertRepositories(repositories: List<RepositoryEntity>) {
    }
}
