package org.contribhub.api.dto.searchkeyword

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.contribhub.core.entity.License

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class LicenseResponse(
    val licenseSeq: Long,
    val licenseName: String,
) {
    companion object {
        fun from(license: License): LicenseResponse =
            LicenseResponse(
                licenseSeq = license.seq,
                licenseName = license.name,
            )
    }
}
