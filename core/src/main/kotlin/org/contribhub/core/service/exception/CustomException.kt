package org.contribhub.core.service.exception

data class CustomException(
    val customExceptionStatus: CustomExceptionStatus,
) : RuntimeException()
