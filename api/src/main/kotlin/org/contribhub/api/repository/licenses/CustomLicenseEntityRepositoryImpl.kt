package org.contribhub.api.repository.licenses

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.contribhub.api.dto.searchkeyword.LicenseResponse
import org.contribhub.api.entity.LicenseEntity
import org.springframework.data.domain.Pageable

class CustomLicenseEntityRepositoryImpl(
    private val jpqlExecutor: KotlinJdslJpqlExecutor,
) : CustomLicenseEntityRepository {
    override fun findLicenseResponsePage(
        lastId: Long,
        pageable: Pageable,
    ): List<LicenseResponse> =
        jpqlExecutor
            .findPage(pageable) {
                selectNew<LicenseResponse>(
                    path(LicenseEntity::licenSeq),
                    path(LicenseEntity::licenName),
                ).from(
                    entity(LicenseEntity::class),
                ).where(
                    path(LicenseEntity::licenSeq).gt(lastId),
                ).orderBy(
                    path(LicenseEntity::licenSeq).asc(),
                )
            }.filterNotNull()
}
