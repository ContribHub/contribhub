package org.contribhub.api.repository.topics

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.contribhub.api.dto.searchkeyword.TopicResponse
import org.contribhub.api.entity.TopicEntity
import org.contribhub.api.infra.http.dto.TopicInfoDTO
import org.springframework.data.domain.Pageable

class CustomTopicEntityRepositoryImpl(
    private val jpqlExecutor: KotlinJdslJpqlExecutor,
) : CustomTopicEntityRepository {
    override fun getTopics(): List<TopicInfoDTO> =
        jpqlExecutor
            .findAll {
                selectNew<TopicInfoDTO>(
                    path(TopicEntity::topicName),
                ).from(
                    entity(TopicEntity::class),
                )
            }.filterNotNull()

    override fun findTopicResponsePage(
        lastId: Long,
        pageable: Pageable,
    ): List<TopicResponse> =
        jpqlExecutor
            .findPage(pageable) {
                selectNew<TopicResponse>(
                    path(TopicEntity::topicSeq),
                    path(TopicEntity::topicDisplayName),
                ).from(
                    entity(TopicEntity::class),
                ).where(
                    path(TopicEntity::topicSeq).gt(lastId),
                ).orderBy(
                    path(TopicEntity::topicSeq).asc(),
                )
            }.filterNotNull()
}
