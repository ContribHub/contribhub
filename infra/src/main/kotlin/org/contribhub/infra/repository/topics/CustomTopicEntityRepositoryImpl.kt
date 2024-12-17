package org.contribhub.infra.repository.topics

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.contribhub.infra.http.dto.TopicInfoDTO
import org.contribhub.infra.repository.entity.TopicEntity
import org.springframework.data.domain.Pageable

class CustomTopicEntityRepositoryImpl(
    private val jpqlExecutor: KotlinJdslJpqlExecutor,
) : CustomTopicEntityRepository {
    override fun getTopics(): List<TopicInfoDTO> =
        jpqlExecutor
            .findAll {
                selectNew<TopicInfoDTO>(path(TopicEntity::topicName))
                    .from(entity(TopicEntity::class))
            }.filterNotNull()

    override fun findTopicResponsePage(
        lastId: Long,
        pageable: Pageable,
    ): List<TopicEntity> =
        jpqlExecutor
            .findPage(pageable) {
                select(entity(TopicEntity::class))
                    .from(entity(TopicEntity::class))
                    .where(path(TopicEntity::topicSeq).gt(lastId))
                    .orderBy(path(TopicEntity::topicSeq).asc())
            }.filterNotNull()
}
