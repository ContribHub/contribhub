package org.contribhub.api.domain.opensourcerepository.service

import org.contribhub.api.common.response.exception.CustomException
import org.contribhub.api.common.response.exception.CustomExceptionStatus
import org.contribhub.api.domain.opensourcerepository.dto.response.IssueListResponse
import org.contribhub.api.domain.opensourcerepository.dto.response.RepositoryDetailResponse
import org.contribhub.api.domain.opensourcerepository.dto.response.RepositoryListResponse
import org.contribhub.api.domain.opensourcerepository.repository.issues.IssueEntityRepository
import org.contribhub.api.domain.opensourcerepository.repository.repositories.RepositoryEntityRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RepositoryService(
    private val repositoryEntityRepository: RepositoryEntityRepository,
    private val issueEntityRepository: IssueEntityRepository,
) {
    @Transactional(readOnly = true)
    fun getRepositoryList(
        lastId: Long,
        size: Int,
    ): List<RepositoryListResponse> {
        val pageable: Pageable = PageRequest.of(0, size)

        return repositoryEntityRepository.findRepositoryListPage(lastId, pageable)
    }

    @Transactional(readOnly = true)
    fun getRepositoryDetail(repoId: Long): RepositoryDetailResponse {
        return repositoryEntityRepository.findRepositoryDetail(repoId)
            ?: throw CustomException(CustomExceptionStatus.NOT_FOUND_REPOSITORY)
    }

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
