package com.alad1nks.dubovozkibackend.users.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("USERS")
data class UserEntity(
    @Id
    val id: Long? = null,
    val email: String,
    val name: String? = null,
    val telegramId: String? = null,
    val password: String,
    val enabled: Boolean = true
)