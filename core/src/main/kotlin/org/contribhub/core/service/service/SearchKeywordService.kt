package org.contribhub.core.service.service

import org.contribhub.core.service.entity.Language
import org.contribhub.core.service.entity.License
import org.contribhub.core.service.entity.Topic
import org.contribhub.core.service.repository.LanguageRepository
import org.contribhub.core.service.repository.LicenseRepository
import org.contribhub.core.service.repository.TopicRepository

class SearchKeywordService(
    private val languageRepository: LanguageRepository,
    private val licenseRepository: LicenseRepository,
    private val topicRepository: TopicRepository,
) {
    fun getLanguageList(
        lastId: Long,
        size: Int,
    ): List<Language> = languageRepository.find(lastId, 0, size)

    fun getLicenseList(
        lastId: Long,
        size: Int,
    ): List<License> = licenseRepository.find(lastId, 0, size)

    fun getTopicList(
        lastId: Long,
        size: Int,
    ): List<Topic> = topicRepository.find(lastId, 0, size)
}
