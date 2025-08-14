package com.airbridge.userservice.adapter.`in`.security

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class JwtExtractorTest {
    @Test
    fun `extract should return null for invalid token`() {
        val extractor = JwtExtractor()
        val result = extractor.extract("invalid.token")
        assertNull(result)
    }

    @Test
    fun `extract should return payload for valid JWT token`() {
        // 예시: header.payload.signature (Base64 인코딩)
        val payload = "{\"sub\":\"user1\"}"
        val encodedPayload = java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(payload.toByteArray())
        val token = "header.$encodedPayload.signature"
        val extractor = JwtExtractor()
        val result = extractor.extract(token)
        assertEquals(payload, result)
    }
}

