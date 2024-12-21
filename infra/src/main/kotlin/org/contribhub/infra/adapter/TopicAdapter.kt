package org.contribhub.infra.adapter

import org.contribhub.core.entity.Topic
import org.contribhub.core.repository.TopicRepository
import org.contribhub.infra.repository.entity.TopicEntity
import org.contribhub.infra.repository.topics.TopicEntityRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class TopicAdapter(
    private val topicRepository: TopicEntityRepository,
) : TopicRepository {
    @Transactional(readOnly = true)
    override fun find(
        lastId: Long,
        pageNumber: Int,
        pageSize: Int,
    ): List<Topic> {
        val pageable: Pageable = PageRequest.of(pageNumber, pageSize)

        return topicRepository
            .findTopicResponsePage(lastId, pageable)
            .map(TopicEntity::toDomain)
    }
}
