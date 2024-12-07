package org.contribhub.api.domain.opensourcerepository.repository.repositories

import org.contribhub.api.domain.opensourcerepository.dto.response.RepositoryListResponse
import org.springframework.data.domain.Pageable

interface CustomRepositoryEntityRepository {
    // 레포지토리 리스트 - 메인페이지
    fun findRepositoryListPage(
        lastId: Long,
        pageable: Pageable,
    ): List<RepositoryListResponse>
}
