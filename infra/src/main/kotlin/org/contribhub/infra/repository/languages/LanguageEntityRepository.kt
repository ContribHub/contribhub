package org.contribhub.infra.repository.languages

import org.contribhub.infra.repository.entity.LanguageEntity
import org.springframework.data.jpa.repository.JpaRepository

interface LanguageEntityRepository :
    JpaRepository<LanguageEntity, Long>,
    CustomLanguageEntityRepository
