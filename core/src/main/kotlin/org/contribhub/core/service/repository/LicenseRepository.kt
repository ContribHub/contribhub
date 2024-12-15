package org.contribhub.core.service.repository

import org.contribhub.core.service.entity.License

interface LicenseRepository {
    fun find(
        lastId: Long,
        pageNumber: Int,
        pageSize: Int
    ): List<License>
}
