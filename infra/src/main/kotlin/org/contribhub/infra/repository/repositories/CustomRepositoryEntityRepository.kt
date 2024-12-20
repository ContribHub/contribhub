package org.contribhub.infra.repository.repositories

import org.contribhub.core.entity.RepositorySearchKey
import org.contribhub.infra.repository.entity.GithubRepositoryEntity
import org.contribhub.infra.repository.entity.RepositoryEntity
import org.springframework.data.domain.Pageable

interface CustomRepositoryEntityRepository {
    // 레포지토리 리스트 - 메인페이지
    fun findRepositoryListPage(
        lastId: Long,
        pageable: Pageable,
        searchKey: RepositorySearchKey,
        repoInTopicList: List<Long>,
    ): List<RepositoryEntity>

    // 레포지토리 상세 조회
    fun findRepositoryDetail(repoId: Long): RepositoryEntity?

    fun upsertRepositories(repositories: List<GithubRepositoryEntity>)
}
