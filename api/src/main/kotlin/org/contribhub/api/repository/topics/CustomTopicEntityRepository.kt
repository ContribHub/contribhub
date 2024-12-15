package org.contribhub.api.repository.topics

import org.contribhub.api.dto.searchkeyword.TopicResponse
import org.contribhub.api.infra.http.dto.TopicInfoDTO
import org.springframework.data.domain.Pageable

interface CustomTopicEntityRepository {
    // github 레포 정보를 가져오기 위한 토픽이름 조회
    fun getTopics(): List<TopicInfoDTO>

    // contribhub에서 검색가능한 토픽 목록 조회.
    fun findTopicResponsePage(
        lastId: Long,
        pageable: Pageable,
    ): List<TopicResponse>
}
