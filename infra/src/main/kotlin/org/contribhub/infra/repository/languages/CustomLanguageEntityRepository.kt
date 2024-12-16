package org.contribhub.infra.repository.languages

import org.contribhub.infra.repository.entity.LanguageEntity
import org.springframework.data.domain.Pageable

interface CustomLanguageEntityRepository {
    fun findLanguageResponsePage(
        lastId: Long,
        pageable: Pageable,
    ): List<LanguageEntity>
}
