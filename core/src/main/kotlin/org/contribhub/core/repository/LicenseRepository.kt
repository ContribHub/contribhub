package org.contribhub.core.repository

import org.contribhub.core.entity.License

interface LicenseRepository {
    fun find(
        lastId: Long,
        pageNumber: Int,
        pageSize: Int,
    ): List<License>
}
