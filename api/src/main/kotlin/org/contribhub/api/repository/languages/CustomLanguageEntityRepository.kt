package org.contribhub.api.repository.languages

import org.contribhub.api.dto.searchkeyword.LanguageResponse
import org.springframework.data.domain.Pageable

interface CustomLanguageEntityRepository {
    fun findLanguageResponsePage(
        lastId: Long,
        pageable: Pageable,
    ): List<LanguageResponse>
}
