package org.contribhub.api.repository.mapping

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.contribhub.api.entity.TopicRepositoryEntity
import org.contribhub.api.entity.TopicRepositoryId

class CustomTopicRepositoryEntityRepositoryImpl(
    private val jpqlExecutor: KotlinJdslJpqlExecutor,
) : CustomTopicRepositoryEntityRepository {
    override fun findRepositoryListInTopic(topicId: Long?): List<Long> {
        // 검색어로 들어온 topicId가 null이면 빈 리스트 반환.
        return topicId?.let {
            jpqlExecutor
                .findAll {
                    selectNew<Long>(
                        path(TopicRepositoryEntity::topicRepositoryId)
                            .path(TopicRepositoryId::repoSeq)
                            .`as`(expression(Long::class, "repoSeq")),
                    ).from(
                        entity(TopicRepositoryEntity::class),
                    ).whereAnd(
                        path(TopicRepositoryEntity::useYn).eq(true),
                        path(TopicRepositoryEntity::topicRepositoryId).path(TopicRepositoryId::topicSeq).eq(topicId),
                    )
                }.filterNotNull()
        } ?: emptyList()
    }
}
