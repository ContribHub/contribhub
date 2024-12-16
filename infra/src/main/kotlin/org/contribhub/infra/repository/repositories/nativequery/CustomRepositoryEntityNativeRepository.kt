package org.contribhub.infra.repository.repositories.nativequery

import org.contribhub.infra.repository.entity.GithubRepositoryEntity

interface CustomRepositoryEntityNativeRepository {
    fun upsertRepositories(repositories: List<GithubRepositoryEntity>): Unit
}
