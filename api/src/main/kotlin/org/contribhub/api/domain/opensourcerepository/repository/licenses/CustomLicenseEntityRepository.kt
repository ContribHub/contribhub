package org.contribhub.api.domain.opensourcerepository.repository.licenses

import org.contribhub.api.domain.opensourcerepository.dto.searchkeyword.LicenseResponse
import org.springframework.data.domain.Pageable

interface CustomLicenseEntityRepository {
    fun findLicenseResponsePage(
        lastId: Long,
        pageable: Pageable,
    ): List<LicenseResponse>
}
