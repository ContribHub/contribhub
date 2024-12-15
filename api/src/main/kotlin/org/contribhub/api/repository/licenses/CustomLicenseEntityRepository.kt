package org.contribhub.api.repository.licenses

import org.contribhub.api.dto.searchkeyword.LicenseResponse
import org.springframework.data.domain.Pageable

interface CustomLicenseEntityRepository {
    fun findLicenseResponsePage(
        lastId: Long,
        pageable: Pageable,
    ): List<LicenseResponse>
}
