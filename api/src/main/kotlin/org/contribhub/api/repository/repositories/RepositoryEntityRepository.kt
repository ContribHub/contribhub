package org.contribhub.api.repository.repositories

import org.contribhub.api.entity.RepositoryEntity
import org.contribhub.api.repository.repositories.nativequery.CustomRepositoryEntityNativeRepository
import org.springframework.data.jpa.repository.JpaRepository

interface RepositoryEntityRepository :
    JpaRepository<RepositoryEntity, Long>,
    CustomRepositoryEntityNativeRepository,
    CustomRepositoryEntityRepository
