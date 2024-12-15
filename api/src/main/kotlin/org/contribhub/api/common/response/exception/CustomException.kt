package org.contribhub.api.common.response.exception

data class CustomException(
    val customExceptionStatus: CustomExceptionStatus,
) : RuntimeException()
