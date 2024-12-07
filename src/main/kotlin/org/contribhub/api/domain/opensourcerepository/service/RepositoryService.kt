package org.contribhub.api.domain.opensourcerepository.service

import org.contribhub.api.domain.opensourcerepository.dto.response.RepositoryListResponse
import org.contribhub.api.domain.opensourcerepository.repository.repositories.RepositoryEntityRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RepositoryService(
    private val repositoryEntityRepository: RepositoryEntityRepository,
) {
    @Transactional(readOnly = true)
    fun getRepositoryList(
        lastId: Long,
        size: Int,
    ): List<RepositoryListResponse> {
        val pageable: Pageable = PageRequest.of(0, size)

        return repositoryEntityRepository.findRepositoryListPage(lastId, pageable)
    }
}
