package org.contribhub.infra.repository.licenses

import org.contribhub.infra.repository.entity.LicenseEntity
import org.springframework.data.domain.Pageable

interface CustomLicenseEntityRepository {
    fun findLicenseResponsePage(
        lastId: Long,
        pageable: Pageable,
    ): List<LicenseEntity>
}
