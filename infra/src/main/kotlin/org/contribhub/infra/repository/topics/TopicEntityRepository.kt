package org.contribhub.infra.repository.topics

import org.contribhub.infra.repository.entity.TopicEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TopicEntityRepository :
    JpaRepository<TopicEntity, Long>,
    CustomTopicEntityRepository
