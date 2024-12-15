package org.contribhub.api.domain.opensourcerepository.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
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
    @Operation(summary = "언어 키워드 조회 API", description = "레포지토리 검색에 사용되는 언어 키워드 목록 조회")
    @GetMapping("/languages")
    fun searchKeywordLanguage(
        @RequestParam(name = "lastId", required = false, defaultValue = "0")
        @Schema(description = "마지막으로 호출한 언어 키워드의 시퀀스 값", example = "2")
        lastId: Long,
        @RequestParam(name = "size", required = false, defaultValue = "10")
        @Schema(description = "한번에 호출할 크기(페이징)", example = "5")
        size: Int,
    ): CustomSuccessResponse<List<LanguageResponse>> {
        return responseService.getCustomSuccessResponse(searchKeywordService.getLanguageList(lastId, size))
    }

    @Operation(summary = "라이센스 키워드 조회 API", description = "레포지토리 검색에 사용되는 라이센스 키워드 목록 조회")
    @GetMapping("/licenses")
    fun searchKeywordLicense(
        @RequestParam(name = "lastId", required = false, defaultValue = "0")
        @Schema(description = "마지막으로 호출한 라이센스 키워드의 시퀀스 값", example = "2")
        lastId: Long,
        @RequestParam(name = "size", required = false, defaultValue = "10")
        @Schema(description = "한번에 호출할 크기(페이징)", example = "5")
        size: Int,
    ): CustomSuccessResponse<List<LicenseResponse>> {
        return responseService.getCustomSuccessResponse(searchKeywordService.getLicenseList(lastId, size))
    }

    @Operation(summary = "토픽 키워드 조회 API", description = "레포지토리 검색에 사용되는 토픽 키워드 목록 조회")
    @GetMapping("/topics")
    fun searchKeywordTopic(
        @RequestParam(name = "lastId", required = false, defaultValue = "0")
        @Schema(description = "마지막으로 호출한 토픽 키워드의 시퀀스 값", example = "2")
        lastId: Long,
        @RequestParam(name = "size", required = false, defaultValue = "10")
        @Schema(description = "한번에 호출할 크기(페이징)", example = "5")
        size: Int,
    ): CustomSuccessResponse<List<TopicResponse>> {
        return responseService.getCustomSuccessResponse(searchKeywordService.getTopicList(lastId, size))
    }
}
