package org.contribhub.api.domain.opensourcerepository.repository.repositories

import org.contribhub.api.domain.opensourcerepository.entity.RepositoryEntity
import org.contribhub.api.domain.opensourcerepository.repository.repositories.nativequery.CustomRepositoryEntityNativeRepository
import org.springframework.data.jpa.repository.JpaRepository

interface RepositoryEntityRepository :
    JpaRepository<RepositoryEntity, Long>,
    CustomRepositoryEntityNativeRepository,
    CustomRepositoryEntityRepository
