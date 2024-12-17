package org.contribhub.infra.repository.licenses

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.contribhub.infra.repository.entity.LicenseEntity
import org.springframework.data.domain.Pageable

class CustomLicenseEntityRepositoryImpl(
    private val jpqlExecutor: KotlinJdslJpqlExecutor,
) : CustomLicenseEntityRepository {
    override fun findLicenseResponsePage(
        lastId: Long,
        pageable: Pageable,
    ): List<LicenseEntity> =
        jpqlExecutor
            .findPage(pageable) {
                select(entity(LicenseEntity::class))
                    .from(entity(LicenseEntity::class))
                    .where(path(LicenseEntity::licenSeq).gt(lastId))
                    .orderBy(path(LicenseEntity::licenSeq).asc())
            }.filterNotNull()
}
