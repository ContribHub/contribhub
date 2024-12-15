package org.contribhub.api.domain.opensourcerepository.repository.issues

import org.contribhub.api.domain.opensourcerepository.entity.IssueEntity
import org.springframework.data.jpa.repository.JpaRepository

interface IssueEntityRepository : JpaRepository<IssueEntity, Long>, CustomIssueEntityRepository
