package org.contribhub.api.dto.searchkeyword

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.contribhub.core.entity.Language

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class LanguageResponse(
    val languageSeq: Long,
    val languageName: String,
) {
    companion object {
        fun from(language: Language): LanguageResponse =
            LanguageResponse(
                languageSeq = language.seq,
                languageName = language.name,
            )
    }
}
