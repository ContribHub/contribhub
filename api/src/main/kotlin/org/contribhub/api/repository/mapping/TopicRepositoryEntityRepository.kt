package org.contribhub.api.repository.mapping

import org.contribhub.api.entity.TopicRepositoryEntity
import org.contribhub.api.entity.TopicRepositoryId
import org.springframework.data.jpa.repository.JpaRepository

interface TopicRepositoryEntityRepository :
    CustomTopicRepositoryEntityRepository,
    JpaRepository<TopicRepositoryEntity, TopicRepositoryId>
