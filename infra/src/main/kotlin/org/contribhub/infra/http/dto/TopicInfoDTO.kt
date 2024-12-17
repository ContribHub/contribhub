package org.contribhub.infra.http.dto

import org.contribhub.core.entity.Topic

data class TopicInfoDTO(
    val name: String,
) {
    fun toDomain(): Topic =
        Topic(name = name)
}
