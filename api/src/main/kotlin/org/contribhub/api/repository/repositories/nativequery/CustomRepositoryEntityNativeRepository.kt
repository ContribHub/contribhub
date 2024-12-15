package org.contribhub.api.repository.repositories.nativequery

import org.contribhub.api.infra.repository.entity.RepositoryEntity

interface CustomRepositoryEntityNativeRepository {
    fun upsertRepositories(repositories: List<RepositoryEntity>): Unit
}
