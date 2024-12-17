package org.contribhub.core.repository

import org.contribhub.core.entity.Issue
import org.contribhub.core.entity.Repository
import org.contribhub.core.entity.RepositoryDetail
import org.contribhub.core.entity.RepositorySearchKey

// todo 네이밍 수정 필요
interface RepositoryRepository {
    fun findRepositories(
        lastId: Long,
        pageNumber: Int,
        pageSize: Int,
        repositorySearchKey: RepositorySearchKey,
    ): List<Repository>

    fun getRepositoryDetail(repoId: Long): RepositoryDetail?

    // 레포지토리 내의 이슈를 조회하는 로직이므로 repository service에 둠.
    fun findIssuesInRepository(
        repoId: Long,
        lastId: Long,
        pageNumber: Int,
        pageSize: Int,
    ): List<Issue>
}
