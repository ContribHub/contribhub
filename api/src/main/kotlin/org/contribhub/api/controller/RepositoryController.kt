package org.contribhub.api.controller

import org.contribhub.api.common.response.ResponseService
import org.contribhub.api.common.response.success.CustomSuccessResponse
import org.contribhub.api.dto.response.IssueListResponse
import org.contribhub.api.dto.response.RepositoryDetailResponse
import org.contribhub.api.dto.response.RepositoryListResponse
import org.contribhub.core.entity.RepositorySearchKey
import org.contribhub.core.service.RepositoryService
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
     * TODO : 검색조건이 늘어나면 body에 넣어서 객체로 검색어 관리하는게 좋아보임.
     *        현재는 단건 조건에 대해서만 처리했는데, 각 키워드가 여러개 들어오는 경우도 고려해야 할듯.
     */

    @GetMapping("/repositories")
    fun getRepositoryList(
        @RequestParam(name = "licenId", required = false) licenId: Long?,
        @RequestParam(name = "topicId", required = false) topicId: Long?,
        @RequestParam(name = "languageId", required = false) languageId: Long?,
        @RequestParam(name = "repoName", required = false) repoName: String?,
        @RequestParam(name = "lastId", required = false, defaultValue = "0") lastId: Long,
        @RequestParam(name = "size", required = false, defaultValue = "10") size: Int,
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
        val repositories = repositoryService.getRepositoryList(lastId, size, searchKey)
        val response = repositories.map(RepositoryListResponse::from)
        return responseService.getCustomSuccessResponse(response)
    }

    @GetMapping("/repositories/{repoId}")
    fun getRepositoryDetail(
        @PathVariable(name = "repoId", required = true) repoId: Long,
    ): CustomSuccessResponse<RepositoryDetailResponse> {
        val repositoryDetail = repositoryService.getRepositoryDetail(repoId)
        val response = RepositoryDetailResponse.from(repositoryDetail)
        return responseService.getCustomSuccessResponse(response)
    }


    @GetMapping("/repositories/{repoId}/issues")
    fun getIssueListInRepository(
        @PathVariable(name = "repoId", required = true) repoId: Long,
        @RequestParam(name = "lastId", required = false, defaultValue = "0") lastId: Long,
        @RequestParam(name = "size", required = false, defaultValue = "10") size: Int,
    ): CustomSuccessResponse<List<IssueListResponse>> {
        val issues = repositoryService.getIssueListInRepository(repoId, lastId, size)
        val response = issues.map(IssueListResponse::from)
        return responseService.getCustomSuccessResponse(response)
    }
}
