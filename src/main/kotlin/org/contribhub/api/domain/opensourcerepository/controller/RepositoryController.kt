package org.contribhub.api.domain.opensourcerepository.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import org.contribhub.api.common.response.ResponseService
import org.contribhub.api.common.response.success.CustomSuccessResponse
import org.contribhub.api.domain.opensourcerepository.dto.request.RepositorySearchKey
import org.contribhub.api.domain.opensourcerepository.dto.response.IssueListResponse
import org.contribhub.api.domain.opensourcerepository.dto.response.RepositoryDetailResponse
import org.contribhub.api.domain.opensourcerepository.dto.response.RepositoryListResponse
import org.contribhub.api.domain.opensourcerepository.service.RepositoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class RepositoryController(
    @Autowired private val responseService: ResponseService,
    @Autowired private val repositoryService: RepositoryService,
) {
    /**
     * TODO : 검색조건이 늘어나면 body에 넣어서 객체로 검색어 관리오픈소스 레포지토리 목하는게 좋아보임.
     *        현재는 단건 조건에 대해서만 처리했는데, 각 키워드가 여러개 들어오는 경우도 고려해야 할듯.
     */
    @Operation(summary = "오픈소스 레포지토리 목록 조회 API", description = "오픈소스 레포지토리 목록 조회")
    @GetMapping("/repositories")
    fun getRepositoryList(
        @RequestParam(name = "licenId", required = false)
        @Schema(description = "검색할 라이센스 시퀀스(seq) 값", example = "2")
        licenId: Long?,
        @RequestParam(name = "topicId", required = false)
        @Schema(description = "검색할 토픽 시퀀스(seq) 값", example = "2")
        topicId: Long?,
        @RequestParam(name = "languageId", required = false)
        @Schema(description = "검색할 언어 시퀀스(seq) 값", example = "2")
        languageId: Long?,
        @RequestParam(name = "repoName", required = false)
        @Schema(description = "검색할 레포지토리 이름", example = "kafka")
        repoName: String?,
        @RequestParam(name = "lastId", required = false, defaultValue = "0")
        @Schema(description = "마지막으로 호출한 레포지토리의 시퀀스(seq) 값", example = "2")
        lastId: Long,
        @RequestParam(name = "size", required = false, defaultValue = "10")
        @Schema(description = "한번에 호출할 크기(페이징)", example = "2")
        size: Int,
    ): CustomSuccessResponse<List<RepositoryListResponse>> {
        // 검색키워드 dto 변환
        val searchKey =
            RepositorySearchKey(
                licenId = licenId,
                topicId = topicId,
                languageId = languageId,
                repoName = repoName,
            )

        // TODO : entity <-> dto간 변환은 별도의 매퍼클래스를 두어 처리하는 것이 좋을 듯 - 당장은 구조가 복잡해지니 아래와 같이 사용
        return responseService.getCustomSuccessResponse(repositoryService.getRepositoryList(lastId, size, searchKey))
    }

    @Operation(summary = "오픈소스 레포지토리 상세정보 조회 API", description = "오픈소스 레포지토리 상세정보 조회")
    @GetMapping("/repositories/{repoId}")
    fun getRepositoryDetail(
        @PathVariable(name = "repoId", required = true)
        @Schema(description = "검색할 레포지토리 시퀀스(seq)", example = "1")
        repoId: Long,
    ): CustomSuccessResponse<RepositoryDetailResponse> {
        return responseService.getCustomSuccessResponse(repositoryService.getRepositoryDetail(repoId))
    }

    @Operation(summary = "오픈소스 레포지토리 내 이슈 목록 조회 API", description = "오픈소스 레포지토리 내 이슈 목록 조회")
    @GetMapping("/repositories/{repoId}/issues")
    fun getIssueListInRepository(
        @PathVariable(name = "repoId", required = true)
        @Schema(description = "레포지토리 시퀀스(seq) 값", example = "2")
        repoId: Long,
        @RequestParam(name = "lastId", required = false, defaultValue = "0")
        @Schema(description = "마지막으로 호출한 이슈의 시퀀스(seq) 값", example = "2")
        lastId: Long,
        @RequestParam(name = "size", required = false, defaultValue = "10")
        @Schema(description = "한번에 호출할 크기(페이징)", example = "2")
        size: Int,
    ): CustomSuccessResponse<List<IssueListResponse>> {
        return responseService.getCustomSuccessResponse(repositoryService.getIssueListInRepository(repoId, lastId, size))
    }
}
