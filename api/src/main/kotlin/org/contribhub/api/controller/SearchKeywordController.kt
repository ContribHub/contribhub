package org.contribhub.api.controller

import org.contribhub.api.common.response.ResponseService
import org.contribhub.api.common.response.success.CustomSuccessResponse
import org.contribhub.core.service.entity.Language
import org.contribhub.core.service.entity.License
import org.contribhub.core.service.entity.Topic
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
    ): CustomSuccessResponse<List<Language>> =
        responseService.getCustomSuccessResponse(searchKeywordService.getLanguageList(lastId, size))

    @GetMapping("/licenses")
    fun searchKeywordLicense(
        @RequestParam(name = "lastId", required = false, defaultValue = "0") lastId: Long,
        @RequestParam(name = "size", required = false, defaultValue = "10") size: Int,
    ): CustomSuccessResponse<List<License>> =
        responseService.getCustomSuccessResponse(searchKeywordService.getLicenseList(lastId, size))

    @GetMapping("/topics")
    fun searchKeywordTopic(
        @RequestParam(name = "lastId", required = false, defaultValue = "0") lastId: Long,
        @RequestParam(name = "size", required = false, defaultValue = "10") size: Int,
    ): CustomSuccessResponse<List<Topic>> =
        responseService.getCustomSuccessResponse(searchKeywordService.getTopicList(lastId, size))
}
