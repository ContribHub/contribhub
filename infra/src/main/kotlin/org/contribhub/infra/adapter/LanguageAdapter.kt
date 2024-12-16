package org.contribhub.infra.adapter

import org.contribhub.core.service.entity.Language
import org.contribhub.core.service.repository.LanguageRepository
import org.contribhub.infra.repository.entity.LanguageEntity
import org.contribhub.infra.repository.languages.LanguageEntityRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class LanguageAdapter(
    private val languageEntityRepository: LanguageEntityRepository,
) : LanguageRepository {
    @Transactional(readOnly = true)
    override fun find(
        lastId: Long,
        pageNumber: Int,
        pageSize: Int,
    ): List<Language> {
        var pageable: Pageable = PageRequest.of(pageNumber, pageSize)

        return languageEntityRepository
            .findLanguageResponsePage(lastId, pageable)
            .map(LanguageEntity::toDomain)
    }
}
