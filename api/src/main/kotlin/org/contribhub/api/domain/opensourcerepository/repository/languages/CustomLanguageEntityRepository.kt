package org.contribhub.api.domain.opensourcerepository.repository.languages

import org.contribhub.api.domain.opensourcerepository.dto.searchkeyword.LanguageResponse
import org.springframework.data.domain.Pageable

interface CustomLanguageEntityRepository {
    fun findLanguageResponsePage(
        lastId: Long,
        pageable: Pageable,
    ): List<LanguageResponse>
}
