package org.contribhub.infra.http.dto

import org.contribhub.core.service.entity.Topic

data class TopicInfoDTO(
    val name: String,
) {
    fun toDomain(): Topic =
        Topic(name = name)
}
