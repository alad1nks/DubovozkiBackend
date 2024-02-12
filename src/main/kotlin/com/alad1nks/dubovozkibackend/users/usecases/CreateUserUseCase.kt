package com.alad1nks.dubovozkibackend.users.usecases

import com.alad1nks.dubovozkibackend.users.UsersRepository
import com.alad1nks.dubovozkibackend.users.entities.UserEntity
import org.springframework.stereotype.Service

@Service
class CreateUserUseCase(
    val repository: UsersRepository
) {
    fun createUser(userEntity: UserEntity) {
        repository.save(userEntity)
    }
}