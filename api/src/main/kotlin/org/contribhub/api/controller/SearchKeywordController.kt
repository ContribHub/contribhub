package org.contribhub.api.controller

import org.contribhub.api.common.response.ResponseService
import org.contribhub.api.common.response.success.CustomSuccessResponse
import org.contribhub.api.dto.searchkeyword.LanguageResponse
import org.contribhub.api.dto.searchkeyword.LicenseResponse
import org.contribhub.api.dto.searchkeyword.TopicResponse
import org.contribhub.core.service.service.SearchKeywordService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchKeywordController(
    private val searchKeywordService: SearchKeywordService,
    private val responseService: ResponseService,
) {
    @GetMapping("/languages")
    fun searchKeywordLanguage(
        @RequestParam(name = "lastId", required = false, defaultValue = "0") lastId: Long,
        @RequestParam(name = "size", required = false, defaultValue = "10") size: Int,
    ): CustomSuccessResponse<List<LanguageResponse>> {
        val languages = searchKeywordService.getLanguageList(lastId, size)
        val response = languages.map(LanguageResponse::from)
        return responseService.getCustomSuccessResponse(response)
    }


    @GetMapping("/licenses")
    fun searchKeywordLicense(
        @RequestParam(name = "lastId", required = false, defaultValue = "0") lastId: Long,
        @RequestParam(name = "size", required = false, defaultValue = "10") size: Int,
    ): CustomSuccessResponse<List<LicenseResponse>> {
        val licenses = searchKeywordService.getLicenseList(lastId, size)
        val response = licenses.map(LicenseResponse::from)
        return responseService.getCustomSuccessResponse(response)
    }


    @GetMapping("/topics")
    fun searchKeywordTopic(
        @RequestParam(name = "lastId", required = false, defaultValue = "0") lastId: Long,
        @RequestParam(name = "size", required = false, defaultValue = "10") size: Int,
    ): CustomSuccessResponse<List<TopicResponse>> {
        val topics = searchKeywordService.getTopicList(lastId, size)
        val response = topics.map(TopicResponse::from)
        return responseService.getCustomSuccessResponse(response)
    }
}
