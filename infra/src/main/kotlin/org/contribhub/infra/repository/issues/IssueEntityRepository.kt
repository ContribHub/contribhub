package org.contribhub.infra.repository.issues

import org.contribhub.infra.repository.entity.IssueEntity
import org.springframework.data.jpa.repository.JpaRepository

interface IssueEntityRepository :
    JpaRepository<IssueEntity, Long>,
    CustomIssueEntityRepository
