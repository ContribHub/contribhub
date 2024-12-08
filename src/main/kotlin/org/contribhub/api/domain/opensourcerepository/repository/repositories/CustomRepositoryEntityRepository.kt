package org.contribhub.api.domain.opensourcerepository.repository.repositories

import org.contribhub.api.infra.repository.entity.RepositoryEntity

interface CustomRepositoryEntityRepository {
    fun upsertRepositories(repositories: List<RepositoryEntity>)
}
