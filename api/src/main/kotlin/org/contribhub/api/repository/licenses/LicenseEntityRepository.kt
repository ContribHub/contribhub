package org.contribhub.api.repository.licenses

import org.contribhub.api.entity.LicenseEntity
import org.springframework.data.jpa.repository.JpaRepository

interface LicenseEntityRepository :
    JpaRepository<LicenseEntity, Long>,
    CustomLicenseEntityRepository
