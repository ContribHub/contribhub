package org.contribhub.api.service

import org.contribhub.api.common.response.exception.CustomException
import org.contribhub.api.common.response.exception.CustomExceptionStatus
import org.contribhub.api.dto.request.RepositorySearchKey
import org.contribhub.api.dto.response.IssueListResponse
import org.contribhub.api.dto.response.RepositoryDetailResponse
import org.contribhub.api.dto.response.RepositoryListResponse
import org.contribhub.api.repository.issues.IssueEntityRepository
import org.contribhub.api.repository.mapping.TopicRepositoryEntityRepository
import org.contribhub.api.repository.repositories.RepositoryEntityRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RepositoryService(
    private val repositoryEntityRepository: RepositoryEntityRepository,
    private val topicRepositoryEntityRepository: TopicRepositoryEntityRepository,
    private val issueEntityRepository: IssueEntityRepository,
) {
    @Transactional(readOnly = true)
    fun getRepositoryList(
        lastId: Long,
        size: Int,
        searchKey: RepositorySearchKey,
    ): List<RepositoryListResponse> {
        val pageable: Pageable = PageRequest.of(0, size)

        val repoInTopicList = topicRepositoryEntityRepository.findRepositoryListInTopic(searchKey.topicId)

        println("test licenId = ${searchKey.licenId}")

        return repositoryEntityRepository.findRepositoryListPage(
            lastId = lastId,
            pageable = pageable,
            searchKey = searchKey,
            repoInTopicList = repoInTopicList,
        )
    }

    @Transactional(readOnly = true)
    fun getRepositoryDetail(repoId: Long): RepositoryDetailResponse =
        repositoryEntityRepository.findRepositoryDetail(repoId)
            ?: throw CustomException(CustomExceptionStatus.NOT_FOUND_REPOSITORY)

    // 레포지토리 내의 이슈를 조회하는 로직이므로 repository service에 둠.
    fun getIssueListInRepository(
        repoId: Long,
        lastId: Long,
        size: Int,
    ): List<IssueListResponse> {
        val pageable: Pageable = PageRequest.of(0, size)

        return issueEntityRepository.findIssueListPage(repoId, lastId, pageable)
    }
}
