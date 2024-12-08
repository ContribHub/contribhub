package org.contribhub.api.domain.opensourcerepository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import org.contribhub.api.domain.opensourcerepository.entity.common.BaseTimeEntity

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

    // 레포지토리의 연관관계는 필요할때 설정.
}
