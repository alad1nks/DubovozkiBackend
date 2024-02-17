package com.alad1nks.dubovozkibackend.admins

import com.alad1nks.dubovozkibackend.admins.entities.AdminEntity
import org.springframework.data.repository.CrudRepository

interface AdminsRepository : CrudRepository<AdminEntity, String> {
    fun existsByEmailAndPassword(email: String, password: String): Boolean
}
