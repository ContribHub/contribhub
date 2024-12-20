package org.contribhub.infra.repository.languages

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.contribhub.infra.repository.entity.LanguageEntity
import org.springframework.data.domain.Pageable

class CustomLanguageEntityRepositoryImpl(
    private val jpqlExecutor: KotlinJdslJpqlExecutor,
) : CustomLanguageEntityRepository {
    override fun findLanguageResponsePage(
        lastId: Long,
        pageable: Pageable,
    ): List<LanguageEntity> =
        jpqlExecutor
            .findPage(pageable) {
                select(entity(LanguageEntity::class))
                    .from(entity(LanguageEntity::class))
                    .where(path(LanguageEntity::languageSeq).gt(lastId))
                    .orderBy(path(LanguageEntity::languageSeq).asc())
            }.filterNotNull()
}
