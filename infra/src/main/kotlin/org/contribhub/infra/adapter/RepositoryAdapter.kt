package org.contribhub.infra.adapter

import org.contribhub.core.service.entity.Issue
import org.contribhub.core.service.entity.Repository
import org.contribhub.core.service.entity.RepositoryDetail
import org.contribhub.core.service.entity.RepositorySearchKey
import org.contribhub.core.service.repository.RepositoryRepository
import org.contribhub.infra.repository.entity.IssueEntity
import org.contribhub.infra.repository.entity.RepositoryEntity
import org.contribhub.infra.repository.issues.IssueEntityRepository
import org.contribhub.infra.repository.mapping.TopicRepositoryEntityRepository
import org.contribhub.infra.repository.repositories.RepositoryEntityRepository
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
        ).map(RepositoryEntity::toDomainRepository)
    }

    @Transactional(readOnly = true)
    override fun getRepositoryDetail(repoId: Long): RepositoryDetail? =
        repositoryEntityRepository.findRepositoryDetail(repoId)?.toDomainRepositoryDetail()

    @Transactional(readOnly = true)
    override fun findIssuesInRepository(repoId: Long, lastId: Long, pageNumber: Int, pageSize: Int): List<Issue> {
        val pageable: Pageable = PageRequest.of(pageNumber, pageSize)

        return issueEntityRepository.findIssueListPage(repoId, lastId, pageable)
            .map(IssueEntity::toDomain)
    }
}