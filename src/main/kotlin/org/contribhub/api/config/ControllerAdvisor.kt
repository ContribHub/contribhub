package org.contribhub.api.config

import io.github.oshai.kotlinlogging.KotlinLogging
import org.contribhub.api.common.response.ResponseService
import org.contribhub.api.common.response.exception.CustomException
import org.contribhub.api.common.response.exception.CustomExceptionResponse
import org.contribhub.api.common.response.exception.CustomExceptionStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Exception
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

// TODO : 에러 세분화가 필요한 경우, 핸들러를 추가해야 함.

/**
 * 컨트롤러까지 올라온 예외를 잡아서 처리하는 부분
 * 예외 공통처리를 위해 생성.
 */
private val logger = KotlinLogging.logger {} // 로그라이브러리 사용을 위해 정의.

@RestControllerAdvice
class ControllerAdvisor(
    @Autowired private val responseService: ResponseService,
) {
    companion object {
        const val DATE_FORMATTER = "yyyy-mm-dd HH"
        const val TIME_ZONE = "Asia/Seoul"
    }

    /**
     * 커스텀 예외 처리.
     */
    @ExceptionHandler(CustomException::class)
    fun customExceptionHandler(exception: CustomException): CustomExceptionResponse {
        val currentTime = LocalDateTime.now(ZoneId.of(TIME_ZONE)).format(DateTimeFormatter.ofPattern(DATE_FORMATTER))
        val status: CustomExceptionStatus = exception.customExceptionStatus

        exception.printStackTrace()
        logger.warn {
            "[CustomException - $currentTime](code : ${status.statusCode}) ${status.message}"
        }

        return responseService.getCustomExceptionResponse(status)
    }

    /**
     * 커스텀 예외를 제외한 나머지 에러 처리
     */
    @ExceptionHandler(Exception::class)
    fun exceptionHandler(exception: Exception): CustomExceptionResponse {
        val currentTime = LocalDateTime.now(ZoneId.of(TIME_ZONE)).format(DateTimeFormatter.ofPattern(DATE_FORMATTER))

        exception.printStackTrace()
        logger.error {
            "[Exception - $currentTime](code : 9999) ${exception.message}"
        }

        return CustomExceptionResponse(
            statusCode = "9999",
            message = exception.message,
        )
    }
}
