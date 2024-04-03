package com.alad1nks.dubovozkibackend.users

import com.alad1nks.dubovozkibackend.users.entities.UserEntity
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface UsersRepository : CrudRepository<UserEntity, Long> {
    fun existsByEmail(email: String): Boolean

    @Query("SELECT id FROM USERS WHERE email = :email")
    fun selectIdByEmail(email: String): Long
}
