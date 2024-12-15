package org.contribhub.core.service.repository

import org.contribhub.core.service.entity.Language

interface LanguageRepository {
    fun find(
        lastId: Long,
        pageNumber: Int,
        pageSize: Int,
    ): List<Language>
}
