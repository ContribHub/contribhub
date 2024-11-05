package org.contribhub.api.domain.opensourcerepository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.contribhub.api.domain.opensourcerepository.entity.common.BaseTimeEntity

@Entity
@Table(name = "repositories")
class RepositoryEntity(
    repoId: String,
    repoName: String,
    repoFullName: String,
    ownerName: String,
    ownerId: String,
    forkCount: Int,
    openIssueCount: Int,
    mainUrl: String,
    repoDescription: String,
    viewCount: String,
    startCount: Int,
    licenseEntity: LicenseEntity,
    languageEntity: LanguageEntity,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repo_seq")
    val repoSeq: Long? = null

    @Column(name = "repo_id")
    var repoId: String = repoId
        protected set

    @Column(name = "repo_name")
    var repoName = repoName
        protected set

    @Column(name = "repo_full_name")
    var repoFullName = repoFullName
        protected set

    @Column(name = "owner_name")
    var ownerName = ownerName
        protected set

    @Column(name = "owner_id")
    var ownerId = ownerId
        protected set

    @Column(name = "fork_count")
    var forkCount = forkCount
        protected set

    @Column(name = "open_issue_count")
    var openIssueCount = openIssueCount
        protected set

    @Column(name = "main_url")
    var mainUrl = mainUrl
        protected set

    @Column(name = "repo_description")
    var repoDescription = repoDescription
        protected set

    @Column(name = "view_count")
    var viewCount = viewCount
        protected set

    @Column(name = "start_count")
    var startCount = startCount
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "licen_seq")
    var licenseEntity = licenseEntity
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_seq")
    var languageEntity = languageEntity
        protected set

    // 토픽_레포지토리 중계테이블과의 연관관계는 필요할때 설정.

    // 토픽_레포지토리 중계테이블과의 연관관계는 필요할때 설정 - 레포조회시, 토픽정보가 필요하기 떄문에 설정.
    @OneToMany(mappedBy = "repositoryEntity", fetch = FetchType.LAZY)
    var topicRepositoryEntity: MutableList<TopicRepositoryEntity> = mutableListOf()
        protected set
}
