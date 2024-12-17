package org.contribhub.api.common.response.success

data class CustomSuccessResponse<T>(
    val message: String,
    val statusCode: String,
    val data: T?,
)
