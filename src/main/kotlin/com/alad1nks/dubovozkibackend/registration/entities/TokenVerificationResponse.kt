package com.alad1nks.dubovozkibackend.registration.entities

data class TokenVerificationResponse(
    val code: Int,
    val token: String? = null
)
