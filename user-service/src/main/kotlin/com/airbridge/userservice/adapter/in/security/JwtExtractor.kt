package com.airbridge.userservice.adapter.`in`.security

class JwtExtractor {
    // JWT 추출 로직 예시
    fun extract(token: String?): String? {
        if (token.isNullOrBlank()) return null
        val parts = token.split(".")
        if (parts.size != 3) return null
        return try {
            val payload = java.util.Base64.getUrlDecoder().decode(parts[1])
            String(payload)
        } catch (e: Exception) {
            null
        }
    }
}
