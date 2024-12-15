package org.contribhub.api.repository.topics

import org.contribhub.api.entity.TopicEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TopicEntityRepository :
    JpaRepository<TopicEntity, Long>,
    CustomTopicEntityRepository
