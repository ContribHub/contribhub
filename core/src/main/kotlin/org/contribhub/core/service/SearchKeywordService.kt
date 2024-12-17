package org.contribhub.core.service

import org.contribhub.core.entity.Language
import org.contribhub.core.entity.License
import org.contribhub.core.entity.Topic
import org.contribhub.core.repository.LanguageRepository
import org.contribhub.core.repository.LicenseRepository
import org.contribhub.core.repository.TopicRepository

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
