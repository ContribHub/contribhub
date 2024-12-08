package org.contribhub.api.domain.opensourcerepository.repository.topics

import org.contribhub.api.domain.opensourcerepository.entity.TopicEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TopicEntityRepository : JpaRepository<TopicEntity, Long>, CustomTopicEntityRepository
