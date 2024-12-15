package org.contribhub.api.dto.searchkeyword

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.contribhub.core.service.entity.License

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class LicenseResponse(
    val licenseSeq: Long,
    val licenseName: String,
) {
    fun toDomain(): License = License(name = licenseName)
}