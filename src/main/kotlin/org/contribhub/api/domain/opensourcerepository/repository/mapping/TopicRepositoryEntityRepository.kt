package org.contribhub.api.domain.opensourcerepository.repository.mapping

import org.contribhub.api.domain.opensourcerepository.entity.TopicRepositoryEntity
import org.contribhub.api.domain.opensourcerepository.entity.TopicRepositoryId
import org.springframework.data.jpa.repository.JpaRepository

interface TopicRepositoryEntityRepository :
    CustomTopicRepositoryEntityRepository,
    JpaRepository<TopicRepositoryEntity, TopicRepositoryId>
