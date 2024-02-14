package com.alad1nks.dubovozkibackend.users.service

import com.alad1nks.dubovozkibackend.admins.AdminsRepository
import com.alad1nks.dubovozkibackend.security.SecurePasswordHashGenerator.generateHash
import com.alad1nks.dubovozkibackend.users.UsersRepository
import com.alad1nks.dubovozkibackend.users.entities.UserEntity
import org.springframework.stereotype.Service

@Service
class CreateUserService(
    val repository: UsersRepository,
    val adminsRepository: AdminsRepository
) {
    operator fun invoke(userEntity: UserEntity, email: String, password: String): String {
        if (adminsRepository.existsByEmailAndPassword(email, password)) {
            repository.save(userEntity.copy(password = generateHash(userEntity.password)))
            return "OK"
        } else {
            return "ERROR"
        }
    }
}
