package org.contribhub.api.dto.searchkeyword

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.contribhub.core.service.entity.Topic

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class TopicResponse(
    val topicSeq: Long,
    val topicName: String,
) {
    fun toDomain(): Topic = Topic(name = topicName)
}
