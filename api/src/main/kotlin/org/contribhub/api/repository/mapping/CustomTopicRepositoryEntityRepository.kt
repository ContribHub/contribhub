package org.contribhub.api.repository.mapping

interface CustomTopicRepositoryEntityRepository {
    // 특정 토픽에 포함된 레포지토리 조회
    fun findRepositoryListInTopic(topicId: Long?): List<Long>
}
