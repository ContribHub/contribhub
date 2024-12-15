package org.contribhub.api.domain.opensourcerepository.repository.languages

import org.contribhub.api.domain.opensourcerepository.entity.LanguageEntity
import org.springframework.data.jpa.repository.JpaRepository

interface LanguageEntityRepository : JpaRepository<LanguageEntity, Long>, CustomLanguageEntityRepository
