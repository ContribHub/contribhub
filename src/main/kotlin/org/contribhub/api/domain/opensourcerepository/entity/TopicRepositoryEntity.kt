package org.contribhub.api.domain.opensourcerepository.entity

import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table
import org.contribhub.api.domain.opensourcerepository.entity.common.BaseTimeEntity

@Entity
@Table(name = "topics_and_repositories")
class TopicRepositoryEntity(
    @EmbeddedId
    val topicRepositoryId: TopicRepositoryId,
    useYn: Boolean,
    repositoryEntity: RepositoryEntity,
    topicEntity: TopicEntity,
) : BaseTimeEntity() {
    @Column(name = "use_yn")
    var useYn = useYn
        protected set

    @MapsId("repoSeq")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repo_seq")
    var repositoryEntity = repositoryEntity
        protected set

    @MapsId("topicSeq")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_seq")
    var topicEntity = topicEntity
        protected set
}
