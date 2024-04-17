package com.alad1nks.dubovozkibackend.security

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.SecretKey

private const val EXPIRATION_TIME_MS: Long = 60 * 60 * 24 * 30 * 1000L

@Component
class JwtTokenUtil(
    @Autowired private val env: Environment
) {
    val jwtSecret: String by lazy {
        env.getRequiredProperty("jwt.secret")
    }

    fun generateToken(email: String, role: String): String {
        val now = Date()
        val expiration = Date(now.time + EXPIRATION_TIME_MS)

        return Jwts.builder()
            .subject(email)
            .claim("role", role)
            .issuedAt(now)
            .expiration(expiration)
            .signWith(getSigningKey(), Jwts.SIG.HS256)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token)
            return true
        } catch (e: JwtException) {
            return false
        }
    }

    fun getEmailFromToken(token: String): String? {
        return try {
            val claims = Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).payload
            claims.subject
        } catch (e: JwtException) {
            null
        }
    }

    fun getRoleFromToken(token: String): String? {
        return try {
            val claims = Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).payload
            claims["role"].toString()
        } catch (e: JwtException) {
            null
        }
    }

    private fun getSigningKey(): SecretKey {
        val keyBytes: ByteArray = jwtSecret.toByteArray(StandardCharsets.UTF_8)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}
