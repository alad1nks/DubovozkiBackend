package com.alad1nks.dubovozkibackend.admins.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("ADMINS")
data class AdminEntity(
    @Id
    val id: Long? = null,
    val email: String,
    val name: String,
    val password: String,
    val rank: Int
)
