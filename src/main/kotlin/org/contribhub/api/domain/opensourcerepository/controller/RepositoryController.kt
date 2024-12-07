package org.contribhub.api.domain.opensourcerepository.controller

import org.contribhub.api.common.response.ResponseService
import org.contribhub.api.common.response.success.CustomSuccessResponse
import org.contribhub.api.domain.opensourcerepository.dto.response.RepositoryListResponse
import org.contribhub.api.domain.opensourcerepository.service.RepositoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class RepositoryController(
    @Autowired private val responseService: ResponseService,
    @Autowired private val repositoryService: RepositoryService,
) {
    @GetMapping("/repositories")
    fun getRepositoryList(
        @RequestParam(name = "lastId", required = false, defaultValue = "0") lastId: Long,
        @RequestParam(name = "size", required = false, defaultValue = "10") size: Int,
    ): CustomSuccessResponse<List<RepositoryListResponse>> {
        // TODO : entity <-> dto간 변환은 별도의 매퍼클래스를 두어 처리하는 것이 좋을 듯 - 당장은 구조가 복잡해지니 아래와 같이 사용
        return responseService.getCustomSuccessResponse(repositoryService.getRepositoryList(lastId, size))
    }
}
