package org.contribhub.api.domain.opensourcerepository.repository.repositories.nativequery

import org.contribhub.api.infra.repository.entity.RepositoryEntity

interface CustomRepositoryEntityNativeRepository {
    fun upsertRepositories(repositories: List<RepositoryEntity>): Unit
}
