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
import jakarta.persistence.UniqueConstraint
import org.contribhub.api.domain.opensourcerepository.entity.common.BaseTimeEntity

@Entity
@Table(
    name = "repositories",
    uniqueConstraints = [
        UniqueConstraint(
            name = "repo_id_unique",
            columnNames = ["repo_id"],
        ),
    ],
)
class RepositoryEntity(
    repoId: String,
    repoName: String,
    repoFullName: String,
    ownerName: String,
    ownerId: String,
    forkCount: Int,
    mainLanauge: String,
    openIssueCount: Int,
    mainUrl: String,
    repoDescription: String,
    starCount: Int,
    licenseEntity: LicenseEntity,
    languageEntity: LanguageEntity,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repo_seq")
    val repoSeq: Long? = null

    @Column(name = "repo_id", columnDefinition = "VARCHAR(20)")
    var repoId: String = repoId
        protected set

    @Column(name = "repo_name", columnDefinition = "VARCHAR(50)")
    var repoName = repoName
        protected set

    @Column(name = "repo_full_name", columnDefinition = "VARCHAR(100)")
    var repoFullName = repoFullName
        protected set

    @Column(name = "owner_name", columnDefinition = "VARCHAR(50)")
    var ownerName = ownerName
        protected set

    @Column(name = "owner_id", columnDefinition = "VARCHAR(20)")
    var ownerId = ownerId
        protected set

    @Column(name = "fork_count", columnDefinition = "integer default 0")
    var forkCount = forkCount
        protected set

    @Column(name = "main_language", columnDefinition = "VARCHAR(20)")
    var mainLanauge = mainLanauge
        protected set

    @Column(name = "open_issue_count", columnDefinition = "integer default 0")
    var openIssueCount = openIssueCount
        protected set

    @Column(name = "main_url", columnDefinition = "VARCHAR(2083)")
    var mainUrl = mainUrl
        protected set

    @Column(name = "repo_description", columnDefinition = "TEXT")
    var repoDescription = repoDescription
        protected set

    @Column(name = "view_count", columnDefinition = "integer default 0")
    var viewCount = 0
        protected set

    @Column(name = "star_count", columnDefinition = "integer default 0")
    var starCount = starCount
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
