package org.contribhub.api.infra.adapter

import org.contribhub.api.dto.searchkeyword.TopicResponse
import org.contribhub.api.infra.http.dto.TopicInfoDTO
import org.contribhub.api.repository.topics.TopicEntityRepository
import org.contribhub.core.service.entity.Topic
import org.contribhub.core.service.repository.TopicRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class TopicAdapter(
    private val topicRepository: TopicEntityRepository
): TopicRepository {
    @Transactional(readOnly = true)
    override fun getTopics(): List<Topic> =
        topicRepository.getTopics().map(TopicInfoDTO::toDomain)

    @Transactional(readOnly = true)
    override fun find(lastId: Long, pageNumber: Int, pageSize: Int): List<Topic> {
        var pageable: Pageable = PageRequest.of(pageNumber, pageSize)

        return topicRepository
            .findTopicResponsePage(lastId, pageable)
            .map(TopicResponse::toDomain)
    }
}