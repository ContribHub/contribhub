package org.contribhub.api.domain.opensourcerepository.dto.searchkeyword

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class TopicResponse(
    val topicSeq: Long,
    val topicName: String,
)
