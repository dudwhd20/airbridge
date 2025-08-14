package com.airbridge.common.jwt

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import java.util.*
import javax.crypto.SecretKey

class JwtTokenProvider(
    secret: String,
    private val validityInMilliseconds: Long = 3600000 // 1시간
) {
    private val secretKey: SecretKey = Keys.hmacShaKeyFor(secret.toByteArray())

    fun createToken(userId: String, role: String): String {
        val claims = mutableMapOf<String, Any>(
            JwtConstants.CLAIM_USER_ID to userId,
            JwtConstants.CLAIM_ROLE to role
        )
        val now = Date()
        val validity = Date(now.time + validityInMilliseconds)
        return Jwts.builder()
            .claims(claims)
            .issuedAt(now)
            .expiration(validity)
            .signWith(secretKey, Jwts.SIG.HS256)
            .compact()
    }

    fun getClaims(token: String): Claims =
        Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).payload

    fun validateToken(token: String): Boolean = try {
        val claims = getClaims(token)
        !claims.expiration.before(Date())
    } catch (e: Exception) {
        false
    }

    fun getUserId(token: String): String? = getClaims(token)[JwtConstants.CLAIM_USER_ID] as? String
    fun getRole(token: String): String? = getClaims(token)[JwtConstants.CLAIM_ROLE] as? String
}
