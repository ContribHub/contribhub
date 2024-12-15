package org.contribhub.api.controller

import org.contribhub.api.common.response.ResponseService
import org.contribhub.api.common.response.success.CustomSuccessResponse
import org.contribhub.api.dto.searchkeyword.LanguageResponse
import org.contribhub.api.dto.searchkeyword.LicenseResponse
import org.contribhub.api.dto.searchkeyword.TopicResponse
import org.contribhub.api.service.SearchKeywordService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchKeywordController(
    @Autowired private val searchKeywordService: SearchKeywordService,
    @Autowired private val responseService: ResponseService,
) {
    @GetMapping("/languages")
    fun searchKeywordLanguage(
        @RequestParam(name = "lastId", required = false, defaultValue = "0") lastId: Long,
        @RequestParam(name = "size", required = false, defaultValue = "10") size: Int,
    ): CustomSuccessResponse<List<LanguageResponse>> =
        responseService.getCustomSuccessResponse(searchKeywordService.getLanguageList(lastId, size))

    @GetMapping("/licenses")
    fun searchKeywordLicense(
        @RequestParam(name = "lastId", required = false, defaultValue = "0") lastId: Long,
        @RequestParam(name = "size", required = false, defaultValue = "10") size: Int,
    ): CustomSuccessResponse<List<LicenseResponse>> =
        responseService.getCustomSuccessResponse(searchKeywordService.getLicenseList(lastId, size))

    @GetMapping("/topics")
    fun searchKeywordTopic(
        @RequestParam(name = "lastId", required = false, defaultValue = "0") lastId: Long,
        @RequestParam(name = "size", required = false, defaultValue = "10") size: Int,
    ): CustomSuccessResponse<List<TopicResponse>> =
        responseService.getCustomSuccessResponse(searchKeywordService.getTopicList(lastId, size))
}
