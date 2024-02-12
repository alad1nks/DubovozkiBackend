package com.alad1nks.dubovozkibackend.users.usecases

import com.alad1nks.dubovozkibackend.security.SecurePasswordHashGenerator.generateHash
import com.alad1nks.dubovozkibackend.users.UsersRepository
import com.alad1nks.dubovozkibackend.users.entities.UserEntity
import org.springframework.stereotype.Service

@Service
class CreateUserUseCase(
    val repository: UsersRepository
) {
    operator fun invoke(userEntity: UserEntity) {
        repository.save(userEntity.copy(password = generateHash(userEntity.password)))
    }
}