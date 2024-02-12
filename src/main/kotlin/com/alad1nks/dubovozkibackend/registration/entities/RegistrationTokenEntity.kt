package com.alad1nks.dubovozkibackend.registration.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("REGISTRATION_TOKENS")
data class RegistrationTokenEntity(
    @Id
    val id: Long? = null,
    val email: String,
    val token: String,
    val expiryDate: Long
)