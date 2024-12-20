package org.contribhub.core.exception

data class CustomException(
    val customExceptionStatus: CustomExceptionStatus,
) : RuntimeException()
