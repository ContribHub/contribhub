package org.contribhub.api.dto.searchkeyword

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.contribhub.core.entity.Topic

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class TopicResponse(
    val topicSeq: Long,
    val topicName: String,
) {
    companion object {
        fun from(topic: Topic): TopicResponse =
            TopicResponse(
                topicSeq = topic.seq,
                topicName = topic.name,
            )
    }
}
