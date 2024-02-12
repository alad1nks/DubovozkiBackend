package com.alad1nks.dubovozkibackend.registration.services

import com.alad1nks.dubovozkibackend.registration.RegistrationTokensRepository
import com.alad1nks.dubovozkibackend.registration.entities.RegistrationTokenRequestBody
import com.alad1nks.dubovozkibackend.security.SecurePasswordHashGenerator.generateHash
import com.alad1nks.dubovozkibackend.users.UsersRepository
import com.alad1nks.dubovozkibackend.users.entities.UserEntity
import org.springframework.stereotype.Service

@Service
class TokenVerificationUseCase(
    val tokensRepository: RegistrationTokensRepository,
    val usersRepository: UsersRepository
) {
    operator fun invoke(tokenBody: RegistrationTokenRequestBody): String {
        val email = tokenBody.email
        val token = tokenBody.token
        return when {
            tokensRepository.existsByEmailAndToken(email, token) -> {
                usersRepository.save(
                    UserEntity(
                        email = email,
                        password = generateHash(token)
                    )
                )
                "YES"
            }
            else -> "NO"
        }
    }
}