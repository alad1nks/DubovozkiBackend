package com.alad1nks.dubovozkibackend.registration.entities

data class RegistrationTokenRequestBody(
    val email: String,
    val token: String,
    val telegramId: String? = null
)
