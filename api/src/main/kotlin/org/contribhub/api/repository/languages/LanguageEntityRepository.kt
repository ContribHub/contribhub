package org.contribhub.api.repository.languages

import org.contribhub.api.entity.LanguageEntity
import org.springframework.data.jpa.repository.JpaRepository

interface LanguageEntityRepository :
    JpaRepository<LanguageEntity, Long>,
    CustomLanguageEntityRepository
