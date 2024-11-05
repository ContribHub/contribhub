package org.contribhub.api.domain.opensourcerepository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.contribhub.api.domain.opensourcerepository.entity.common.BaseTimeEntity

@Entity
@Table(name = "Issues")
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

    @Column(name = "issue_id")
    var issueId = issueId
        protected set

    @Column(name = "issue_title")
    var issueTitle = issueTitle
        protected set

    @Column(name = "issue_url")
    var issueUrl = issueUrl
        protected set

    @Column(name = "issue_owner_name")
    var issueOwnerName = issueOwnerName
        protected set

    @Column(name = "issue_owner_id")
    var issueOwnerId = issueOwnerId
        protected set

    @Column(name = "open_yn")
    var openYn = openYn
        protected set

    // 레포지토리의 연관관계는 필요할때 설정.
}
