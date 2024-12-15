package org.contribhub.api.repository.languages

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.contribhub.api.dto.searchkeyword.LanguageResponse
import org.contribhub.api.entity.LanguageEntity
import org.springframework.data.domain.Pageable

class CustomLanguageEntityRepositoryImpl(
    private val jpqlExecutor: KotlinJdslJpqlExecutor,
) : CustomLanguageEntityRepository {
    override fun findLanguageResponsePage(
        lastId: Long,
        pageable: Pageable,
    ): List<LanguageResponse> =
        jpqlExecutor
            .findPage(pageable) {
                selectNew<LanguageResponse>(
                    path(LanguageEntity::languageSeq),
                    path(LanguageEntity::language),
                ).from(
                    entity(LanguageEntity::class),
                ).where(
                    path(LanguageEntity::languageSeq).gt(lastId),
                ).orderBy(
                    path(LanguageEntity::languageSeq).asc(),
                )
            }.filterNotNull()
}
