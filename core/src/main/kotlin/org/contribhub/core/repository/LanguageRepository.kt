package org.contribhub.core.repository

import org.contribhub.core.entity.Language

interface LanguageRepository {
    fun find(
        lastId: Long,
        pageNumber: Int,
        pageSize: Int,
    ): List<Language>
}
