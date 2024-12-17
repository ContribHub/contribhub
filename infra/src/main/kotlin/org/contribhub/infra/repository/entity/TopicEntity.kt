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
import org.contribhub.core.entity.Topic
import org.contribhub.infra.repository.entity.common.BaseTimeEntity

@Entity
@Table(
    name = "topics",
    uniqueConstraints = [
        UniqueConstraint(
            name = "topic_name_unique",
            columnNames = ["topic_name"],
        ),
    ],
)
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

    @Column(name = "topic_name", columnDefinition = "VARCHAR(100)")
    var topicName = topicName
        protected set

    @Column(name = "topic_display_name", columnDefinition = "VARCHAR(500)")
    var topicDisplayName = topicDisplayName
        protected set

    @Column(name = "stort_description", columnDefinition = "VARCHAR(500)")
    var shortDescription = shortDescription
        protected set

    @Column(name = "description", columnDefinition = "TEXT")
    var description = description
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repo_seq")
    var repositoryEntity = repositoryEntity

    // 토픽_레포지토리 중계테이블과의 연관관계는 필요할때 설정.

    fun toDomain(): Topic =
        Topic(name = topicName)
}
