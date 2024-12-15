package org.contribhub.api.service

import org.contribhub.api.dto.searchkeyword.LanguageResponse
import org.contribhub.api.dto.searchkeyword.LicenseResponse
import org.contribhub.api.dto.searchkeyword.TopicResponse
import org.contribhub.api.repository.languages.LanguageEntityRepository
import org.contribhub.api.repository.licenses.LicenseEntityRepository
import org.contribhub.api.repository.topics.TopicEntityRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SearchKeywordService(
    private val languageEntityRepository: LanguageEntityRepository,
    private val licenseEntityRepository: LicenseEntityRepository,
    private val topicEntityRepository: TopicEntityRepository,
) {
    @Transactional(readOnly = true)
    fun getLanguageList(
        lastId: Long,
        size: Int,
    ): List<LanguageResponse> {
        var pageable: Pageable = PageRequest.of(0, size)

        return languageEntityRepository.findLanguageResponsePage(lastId, pageable)
    }

    @Transactional(readOnly = true)
    fun getLicenseList(
        lastId: Long,
        size: Int,
    ): List<LicenseResponse> {
        var pageable: Pageable = PageRequest.of(0, size)

        return licenseEntityRepository.findLicenseResponsePage(lastId, pageable)
    }

    @Transactional(readOnly = true)
    fun getTopicList(
        lastId: Long,
        size: Int,
    ): List<TopicResponse> {
        var pageable: Pageable = PageRequest.of(0, size)

        return topicEntityRepository.findTopicResponsePage(lastId, pageable)
    }
}
