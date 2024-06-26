package com.alad1nks.dubovozkibackend.users.service

import com.alad1nks.dubovozkibackend.users.UsersRepository
import com.alad1nks.dubovozkibackend.users.entities.UserEntity
import org.springframework.stereotype.Service

@Service
class CreateUserService(
    val repository: UsersRepository
) {
    operator fun invoke(userEntity: UserEntity): String {
        repository.save(userEntity)
        return "OK"
    }
}
