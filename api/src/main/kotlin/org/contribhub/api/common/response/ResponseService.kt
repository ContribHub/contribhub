package org.contribhub.api.common.response

import org.contribhub.api.common.response.exception.CustomExceptionResponse
import org.contribhub.core.exception.CustomExceptionStatus
import org.contribhub.api.common.response.success.CustomSuccessResponse
import org.contribhub.api.common.response.success.CustomSuccessStatus
import org.springframework.stereotype.Service

@Service
class ResponseService {
    // TODO : 추후에 정상응답, 예외응답외의 추가 형태가 필요하거나 정상/예외 응답을 세분화할 필요가 있으면 변경필요

    /**
     * 정상응답
     */
    fun <T> getCustomSuccessResponse(
        data: T? = null,
        customSuccessStatus: CustomSuccessStatus = CustomSuccessStatus.RESPONSE_SUCCESS,
    ): CustomSuccessResponse<T> {
        return CustomSuccessResponse<T>(
            statusCode = customSuccessStatus.statusCode,
            message = customSuccessStatus.message,
            data = data,
        )
    }

    /**
     * 예외응답
     */
    fun getCustomExceptionResponse(customExceptionStatus: CustomExceptionStatus): CustomExceptionResponse {
        return CustomExceptionResponse(
            statusCode = customExceptionStatus.statusCode,
            message = customExceptionStatus.message,
        )
    }
}
