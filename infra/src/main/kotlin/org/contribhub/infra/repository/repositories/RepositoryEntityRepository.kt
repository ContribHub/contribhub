package org.contribhub.infra.repository.repositories

import org.contribhub.infra.repository.entity.RepositoryEntity
import org.contribhub.infra.repository.repositories.nativequery.CustomRepositoryEntityNativeRepository
import org.springframework.data.jpa.repository.JpaRepository

interface RepositoryEntityRepository :
    JpaRepository<RepositoryEntity, Long>,
    CustomRepositoryEntityNativeRepository,
    CustomRepositoryEntityRepository
