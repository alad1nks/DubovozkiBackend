package com.alad1nks.dubovozkibackend.registration.services

import com.alad1nks.dubovozkibackend.registration.RegistrationTokensRepository
import com.alad1nks.dubovozkibackend.registration.entities.RegistrationTokenRequestBody
import com.alad1nks.dubovozkibackend.users.UsersRepository
import com.alad1nks.dubovozkibackend.users.entities.UserEntity
import org.springframework.stereotype.Service

@Service
class TokenVerificationService(
    val tokensRepository: RegistrationTokensRepository,
    val usersRepository: UsersRepository
) {

    fun verify(tokenBody: RegistrationTokenRequestBody): String {
        val email = tokenBody.email
        val token = tokenBody.token
        return when {
            tokensRepository.existsByEmailAndToken(email, token) -> {
                usersRepository.save(
                    UserEntity(
                        email = email,
                        name = "Gans",
                        password = "sas",
                        enabled = true
                    )
                )
                "YES"
            }
            else -> "No"
        }
    }

}