package org.contribhub.api.domain.opensourcerepository.controller

import org.contribhub.api.common.response.ResponseService
import org.contribhub.api.common.response.success.CustomSuccessResponse
import org.contribhub.api.domain.opensourcerepository.dto.searchkeyword.LanguageResponse
import org.contribhub.api.domain.opensourcerepository.dto.searchkeyword.LicenseResponse
import org.contribhub.api.domain.opensourcerepository.dto.searchkeyword.TopicResponse
import org.contribhub.api.domain.opensourcerepository.service.SearchKeywordService
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
    ): CustomSuccessResponse<List<LanguageResponse>> {
        return responseService.getCustomSuccessResponse(searchKeywordService.getLanguageList(lastId, size))
    }

    @GetMapping("/licenses")
    fun searchKeywordLicense(
        @RequestParam(name = "lastId", required = false, defaultValue = "0") lastId: Long,
        @RequestParam(name = "size", required = false, defaultValue = "10") size: Int,
    ): CustomSuccessResponse<List<LicenseResponse>> {
        return responseService.getCustomSuccessResponse(searchKeywordService.getLicenseList(lastId, size))
    }

    @GetMapping("/topics")
    fun searchKeywordTopic(
        @RequestParam(name = "lastId", required = false, defaultValue = "0") lastId: Long,
        @RequestParam(name = "size", required = false, defaultValue = "10") size: Int,
    ): CustomSuccessResponse<List<TopicResponse>> {
        return responseService.getCustomSuccessResponse(searchKeywordService.getTopicList(lastId, size))
    }
}
