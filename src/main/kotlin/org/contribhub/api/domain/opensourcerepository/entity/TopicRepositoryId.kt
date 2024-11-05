package org.contribhub.api.domain.opensourcerepository.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class TopicRepositoryId(
    @Column(name = "topic_seq")
    val topicSeq: Long,
    @Column(name = "repo_seq")
    val repoSeq: Long,
) : Serializable
