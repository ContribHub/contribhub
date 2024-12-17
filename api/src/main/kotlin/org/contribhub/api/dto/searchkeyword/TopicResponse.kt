package org.contribhub.api.dto.searchkeyword

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.contribhub.core.service.entity.Topic

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class TopicResponse(
    val topicName: String,
) {
    companion object {
        fun from(topic: Topic): TopicResponse =
            TopicResponse(topicName = topic.name)
    }
}