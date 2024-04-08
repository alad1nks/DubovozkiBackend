package com.alad1nks.dubovozkibackend.registration.service

import com.alad1nks.dubovozkibackend.registration.RegistrationTokensRepository
import com.alad1nks.dubovozkibackend.registration.entities.RegistrationTokenRequestBody
import com.alad1nks.dubovozkibackend.security.JwtTokenUtil.generateToken
import com.alad1nks.dubovozkibackend.users.UsersRepository
import com.alad1nks.dubovozkibackend.users.entities.UserEntity
import com.alad1nks.dubovozkibackend.users.entities.UserRole
import org.springframework.stereotype.Service

@Service
class TokenVerificationUseCase(
    val tokensRepository: RegistrationTokensRepository,
    val usersRepository: UsersRepository
) {
    operator fun invoke(request: RegistrationTokenRequestBody): String {
        with(request) {
            val userExists = usersRepository.existsByEmail(email)
            val tokenBody = tokensRepository.findByEmail(email)

            when {
                tokenBody == null -> {
                    return "NO"
                }

                tokenBody.tries <= 0 -> {
                    return "NO"
                }

                tokenBody.token != token -> {
                    tokenBody.id?.let { tokensRepository.updateTokenTries(it) }
                    return "NO"
                }

                userExists -> {
                    return generateToken(email, UserRole.USER.name)
                }

                else -> {
                    usersRepository.save(
                        UserEntity(
                            email = email,
                            telegramId = telegramId
                        )
                    )
                    tokensRepository.deleteByEmail(email)
                    return generateToken(email, UserRole.USER.name)
                }
            }
        }
    }
}
