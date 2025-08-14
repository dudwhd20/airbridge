package com.airbridge.common.exception


enum class ErrorCode(val message: String, val status: Int) {
    INVALID_TOKEN("유효하지 않은 토큰입니다.", 401),
    EXPIRED_TOKEN("토큰이 만료되었습니다.", 401),
    ACCESS_DENIED("접근이 거부되었습니다.", 403),
    INTERNAL_ERROR("서버 내부 오류", 500)
}
