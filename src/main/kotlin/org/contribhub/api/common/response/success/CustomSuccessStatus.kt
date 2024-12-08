package org.contribhub.api.common.response.success

enum class CustomSuccessStatus(
    val message: String,
    val statusCode: String,
) {
    // TODO : 아래 응답은 예시로 실제 서비스에 맞게 추가하면 됨.
    RESPONSE_SUCCESS("요청에 성공했습니다.", "200"),
    RESPONSE_NO_CONTENT("조회된 데이터가 없습니다", "204"),
}
