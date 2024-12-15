package org.contribhub.api.repository.issues

import org.contribhub.api.entity.IssueEntity
import org.springframework.data.jpa.repository.JpaRepository

interface IssueEntityRepository :
    JpaRepository<IssueEntity, Long>,
    CustomIssueEntityRepository
