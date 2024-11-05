package org.contribhub.api.domain.opensourcerepository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.contribhub.api.domain.opensourcerepository.entity.common.BaseTimeEntity

@Entity
@Table(name = "Topics")
class TopicEntity(
    topicName: String,
    topicDisplayName: String,
    shortDescription: String,
    description: String,
    repositoryEntity: RepositoryEntity,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_seq")
    val topicSeq: Long? = null

    @Column(name = "topic_name")
    var topicName = topicName
        protected set

    @Column(name = "topic_disply_name")
    var topicDisplayName = topicDisplayName
        protected set

    @Column(name = "stort_description")
    var shortDescription = shortDescription
        protected set

    @Column(name = "description")
    var description = description
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repo_seq")
    var repositoryEntity = repositoryEntity

    // 토픽_레포지토리 중계테이블과의 연관관계는 필요할때 설정.
}
