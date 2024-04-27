package com.alad1nks.dubovozkibackend.users.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("USERS")
data class UserEntity(
    val email: String,
    @Id
    val id: Long? = null,
    val role: UserRole = UserRole.USER,
    val enabled: Boolean = true
)
