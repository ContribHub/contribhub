package org.contribhub.api.infra.adapter

import org.contribhub.api.dto.searchkeyword.LicenseResponse
import org.contribhub.api.repository.licenses.LicenseEntityRepository
import org.contribhub.core.service.entity.License
import org.contribhub.core.service.repository.LicenseRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class LicenseAdapter(
    private val licenseRepository: LicenseEntityRepository
) : LicenseRepository {
    override fun find(lastId: Long, pageNumber: Int, pageSize: Int): List<License> {
        var pageable: Pageable = PageRequest.of(pageNumber, pageSize)

        return licenseRepository
            .findLicenseResponsePage(lastId, pageable)
            .map(LicenseResponse::toDomain)
    }
}