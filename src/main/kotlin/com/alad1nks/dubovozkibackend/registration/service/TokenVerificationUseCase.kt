package com.alad1nks.dubovozkibackend.registration.service

import com.alad1nks.dubovozkibackend.registration.RegistrationTokensRepository
import com.alad1nks.dubovozkibackend.registration.entities.RegistrationResponse
import com.alad1nks.dubovozkibackend.registration.entities.RegistrationTokenRequestBody
import com.alad1nks.dubovozkibackend.registration.entities.TokenVerificationResponse
import com.alad1nks.dubovozkibackend.security.JwtTokenUtil
import com.alad1nks.dubovozkibackend.users.UsersRepository
import com.alad1nks.dubovozkibackend.users.entities.UserEntity
import com.alad1nks.dubovozkibackend.users.entities.UserRole
import org.springframework.stereotype.Service

@Service
class TokenVerificationUseCase(
    val tokensRepository: RegistrationTokensRepository,
    val usersRepository: UsersRepository,
    val jwtUtil: JwtTokenUtil
) {
    operator fun invoke(request: RegistrationTokenRequestBody): TokenVerificationResponse {
        with(request) {
            val userExists = usersRepository.existsByEmail(email)
            val tokenEntity = tokensRepository.findByEmail(email)

            when {
                tokenEntity == null -> {
                    return RegistrationResponse.EmailDoesNotExist.response()
                }

                tokenEntity.tries <= 0 -> {
                    return RegistrationResponse.RetriesAreExhausted.response()
                }

                tokenEntity.token != token -> {
                    tokenEntity.id?.let { tokensRepository.updateTokenTries(it) }
                    return RegistrationResponse.IncorrectActivationCode.response()
                }

                userExists -> {
                    val jwt = jwtUtil.generateToken(email, UserRole.USER.name)
                    return RegistrationResponse.Success.response(jwt)
                }

                else -> {
                    usersRepository.save(
                        UserEntity(
                            email = email,
                            telegramId = telegramId
                        )
                    )
                    tokensRepository.deleteByEmail(email)
                    val jwt = jwtUtil.generateToken(email, UserRole.USER.name)
                    return RegistrationResponse.Success.response(jwt)
                }
            }
        }
    }
}
