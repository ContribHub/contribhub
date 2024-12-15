package org.contribhub.core.service.repository

import org.contribhub.core.service.entity.Topic

interface TopicRepository {
    fun getTopics(): List<Topic>

    fun find(
        lastId: Long,
        pageNumber: Int,
        pageSize: Int
    ): List<Topic>
}
