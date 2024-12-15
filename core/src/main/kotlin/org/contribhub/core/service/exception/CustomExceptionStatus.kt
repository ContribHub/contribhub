package org.contribhub.core.service.exception

enum class CustomExceptionStatus(
    val message: String,
    val statusCode: String,
) {
    // TODO : 아래 응답은 예시로 실제 서비스에 맞게 추가하면 됨.
    NOT_FOUND_REPOSITORY("레포지토리를 찾을 수 없습니다.", "404"),
    NOT_FOUND_ISSUE("이슈를 찾을 수 없습니다.", "404"),
}
