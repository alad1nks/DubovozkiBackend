package com.alad1nks.dubovozkibackend.users

import com.alad1nks.dubovozkibackend.users.entities.UserEntity
import org.springframework.data.repository.CrudRepository

interface UsersRepository : CrudRepository<UserEntity, Long> {
    fun existsByEmail(email: String): Boolean
}
