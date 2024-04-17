package com.alad1nks.dubovozkibackend.users.service

import com.alad1nks.dubovozkibackend.users.UsersRepository
import com.alad1nks.dubovozkibackend.users.entities.UserEntity
import org.springframework.stereotype.Service

@Service
class GetUserListService(
    val repository: UsersRepository
) {
    operator fun invoke(): List<UserEntity> {
        return repository.findAll().toList()
    }
}
