package org.contribhub.infra.repository.licenses

import org.contribhub.infra.repository.entity.LicenseEntity
import org.springframework.data.jpa.repository.JpaRepository

interface LicenseEntityRepository :
    JpaRepository<LicenseEntity, Long>,
    CustomLicenseEntityRepository
