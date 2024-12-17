package org.contribhub.core.repository

import org.contribhub.core.entity.Topic

interface TopicRepository {
    fun getTopics(): List<Topic>

    fun find(
        lastId: Long,
        pageNumber: Int,
        pageSize: Int
    ): List<Topic>
}
