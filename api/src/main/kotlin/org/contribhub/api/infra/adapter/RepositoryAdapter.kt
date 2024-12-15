package org.contribhub.api.infra.adapter

import org.contribhub.api.dto.response.IssueListResponse
import org.contribhub.api.dto.response.RepositoryListResponse
import org.contribhub.api.repository.issues.IssueEntityRepository
import org.contribhub.api.repository.mapping.TopicRepositoryEntityRepository
import org.contribhub.api.repository.repositories.RepositoryEntityRepository
import org.contribhub.core.service.entity.Issue
import org.contribhub.core.service.entity.Repository
import org.contribhub.core.service.entity.RepositoryDetail
import org.contribhub.core.service.entity.RepositorySearchKey
import org.contribhub.core.service.repository.RepositoryRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class RepositoryAdapter(
    private val repositoryEntityRepository: RepositoryEntityRepository,
    private val topicRepositoryEntityRepository: TopicRepositoryEntityRepository,
    private val issueEntityRepository: IssueEntityRepository,
) : RepositoryRepository{
    @Transactional(readOnly = true)
    override fun findRepositories(
        lastId: Long,
        pageNumber: Int,
        pageSize: Int,
        repositorySearchKey: RepositorySearchKey,
    ): List<Repository> {
        val pageable: Pageable = PageRequest.of(pageNumber, pageSize)

        val repoInTopicList = topicRepositoryEntityRepository.findRepositoryListInTopic(repositorySearchKey.topicId)

        return repositoryEntityRepository.findRepositoryListPage(
            lastId = lastId,
            pageable = pageable,
            searchKey = repositorySearchKey,
            repoInTopicList = repoInTopicList,
        ).map(RepositoryListResponse::toDomain)
    }

    @Transactional(readOnly = true)
    override fun getRepositoryDetail(repoId: Long): RepositoryDetail? =
        repositoryEntityRepository.findRepositoryDetail(repoId)?.toDomain()

    @Transactional(readOnly = true)
    override fun findIssuesInRepository(repoId: Long, lastId: Long, pageNumber: Int, pageSize: Int): List<Issue> {
        val pageable: Pageable = PageRequest.of(pageNumber, pageSize)

        return issueEntityRepository.findIssueListPage(repoId, lastId, pageable)
            .map(IssueListResponse::toDomain)
    }
}