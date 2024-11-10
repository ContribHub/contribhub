package org.contribhub.api.infra.repository

import org.contribhub.api.infra.repository.entity.RepositoryEntity
import org.springframework.stereotype.Repository

// TODO Github Repository <-> @Repository 구분 필요
// TODO Github Repository <-> Domain Repository 구분 필요
interface RepositoryJpaRepository {
    fun upsertRepositories(repositories: List<RepositoryEntity>)
}

// TODO
@Repository
class MockRepositoryJpaRepository : RepositoryJpaRepository {
    override fun upsertRepositories(repositories: List<RepositoryEntity>) {
    }
}
