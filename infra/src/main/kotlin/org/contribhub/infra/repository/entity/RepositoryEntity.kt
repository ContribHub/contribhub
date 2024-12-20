package org.contribhub.infra.repository.entity

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
import org.contribhub.core.entity.Repository
import org.contribhub.core.entity.RepositoryDetail
import org.contribhub.infra.repository.entity.common.BaseTimeEntity

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
    openIssueCount: Int,
    mainUrl: String,
    repoDescription: String,
    starCount: Int,
    viewCount: Int,
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
    var viewCount = viewCount
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

    // 토픽_레포지토리 중계테이블과의 연관관계는 필요할때 설정 - 레포조회시, 토픽정보가 필요하기 떄문에 설정.
    @OneToMany(mappedBy = "repositoryEntity", fetch = FetchType.LAZY)
    var topicRepositoryEntity: MutableList<TopicRepositoryEntity> = mutableListOf()
        protected set

    // 이슈 목록은 필요함 - 특정 레포지토리에 포함된 이슈리스트 조회시 사용
    @OneToMany(mappedBy = "repositoryEntity", fetch = FetchType.LAZY)
    var issueEntity: MutableList<IssueEntity> = mutableListOf()

    // TODO: repoId는 왜 타입이 통일이 안되는지?
    fun toDomainRepository(): Repository =
        Repository(
            repoId = repoId.toLong(),
            repoName = repoName,
            repoOwnerId = ownerId,
            repoOwnerName = ownerName,
            openIssueCount = openIssueCount,
            repoUrl = mainUrl,
            viewCount = viewCount,
            starCount = starCount,
            licenseName = licenseEntity.licenName,
            mainLanguage = languageEntity.language,
            forkCount = forkCount,
        )

    fun toDomainRepositoryDetail(): RepositoryDetail =
        RepositoryDetail(
            repoId = repoId.toLong(),
            repoName = repoName,
            repoOwnerId = ownerId,
            repoOwnerName = ownerName,
            openIssueCount = openIssueCount,
            repoUrl = mainUrl,
            viewCount = viewCount,
            starCount = starCount,
            licenseName = licenseEntity.licenName,
            mainLanguage = languageEntity.language,
            forkCount = forkCount,
            repoFullName = repoFullName,
            repoDescription = repoDescription,
        )
}
