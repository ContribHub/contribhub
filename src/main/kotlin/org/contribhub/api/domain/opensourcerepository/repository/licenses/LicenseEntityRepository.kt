package org.contribhub.api.domain.opensourcerepository.repository.licenses

import org.contribhub.api.domain.opensourcerepository.entity.LicenseEntity
import org.springframework.data.jpa.repository.JpaRepository

interface LicenseEntityRepository : JpaRepository<LicenseEntity, Long>, CustomLicenseEntityRepository
