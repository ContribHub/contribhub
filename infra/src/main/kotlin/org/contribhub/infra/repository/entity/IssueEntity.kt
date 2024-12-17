package org.contribhub.infra.repository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import org.contribhub.core.entity.Issue
import org.contribhub.infra.repository.entity.common.BaseTimeEntity

@Entity
@Table(
    name = "issues",
    uniqueConstraints = [
        UniqueConstraint(
            name = "issue_id_unique",
            columnNames = ["issue_id"],
        ),
    ],
)
class IssueEntity(
    issueId: String,
    issueTitle: String,
    issueUrl: String,
    issueOwnerName: String,
    issueOwnerId: String,
    openYn: Boolean,
    repositoryEntity: RepositoryEntity,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue_seq")
    val issueSeq: Long? = null

    @Column(name = "issue_id", columnDefinition = "VARCHAR(10)")
    var issueId = issueId
        protected set

    @Column(name = "issue_title", columnDefinition = "VARCHAR(200)")
    var issueTitle = issueTitle
        protected set

    @Column(name = "issue_url", columnDefinition = "VARCHAR(2083)")
    var issueUrl = issueUrl
        protected set

    @Column(name = "issue_owner_name", columnDefinition = "VARCHAR(50)")
    var issueOwnerName = issueOwnerName
        protected set

    @Column(name = "issue_owner_id", columnDefinition = "VARCHAR(20)")
    var issueOwnerId = issueOwnerId
        protected set

    @Column(name = "open_yn", columnDefinition = "boolean default true")
    var openYn = openYn
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repo_seq")
    var repositoryEntity = repositoryEntity
        protected set


    fun toDomain(): Issue =
        Issue(
            id = issueSeq!!,
            issueId = issueId,
            issueTitle = issueTitle,
            issueUrl = issueUrl,
            issueOwnerId = issueOwnerId,
            issueOwnerName =  issueOwnerName,
            openYn = openYn
        )
}
