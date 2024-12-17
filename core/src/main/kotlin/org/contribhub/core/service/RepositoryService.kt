package org.contribhub.core.service

import org.contribhub.core.entity.Issue
import org.contribhub.core.entity.Repository
import org.contribhub.core.entity.RepositoryDetail
import org.contribhub.core.entity.RepositorySearchKey
import org.contribhub.core.exception.CustomException
import org.contribhub.core.exception.CustomExceptionStatus
import org.contribhub.core.repository.RepositoryRepository

class RepositoryService(
    private val repositoryRepository: RepositoryRepository,
) {
    fun getRepositoryList(
        lastId: Long,
        size: Int,
        repositorySearchKey: RepositorySearchKey,
    ): List<Repository> =
        repositoryRepository.findRepositories(
            lastId = lastId,
            pageNumber = 0,
            pageSize = size,
            repositorySearchKey,
        )

    fun getRepositoryDetail(repoId: Long): RepositoryDetail =
        repositoryRepository.getRepositoryDetail(repoId)
            ?: throw CustomException(CustomExceptionStatus.NOT_FOUND_REPOSITORY)

    // 레포지토리 내의 이슈를 조회하는 로직이므로 repository service에 둠.
    fun getIssueListInRepository(
        repoId: Long,
        lastId: Long,
        size: Int,
    ): List<Issue> = repositoryRepository.findIssuesInRepository(repoId, lastId, pageNumber = 0, pageSize = size)
}
