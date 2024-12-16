package org.contribhub.infra.repository.mapping

import org.contribhub.infra.repository.entity.TopicRepositoryEntity
import org.contribhub.infra.repository.entity.TopicRepositoryId
import org.springframework.data.jpa.repository.JpaRepository

interface TopicRepositoryEntityRepository :
    CustomTopicRepositoryEntityRepository,
    JpaRepository<TopicRepositoryEntity, TopicRepositoryId>
