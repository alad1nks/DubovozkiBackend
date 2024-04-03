package com.alad1nks.dubovozkibackend.registration.service

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
        with(tokenBody) {
            when {
                !tokensRepository.existsByEmailAndToken(email, token) -> {
                    return "NO"
                }
                usersRepository.existsByEmail(email) -> {
                    usersRepository.save(
                        UserEntity(
                            id = usersRepository.selectIdByEmail(email),
                            email = email,
                            telegramId = telegramId,
                            password = generateHash(token)
                        )
                    )
                    tokensRepository.deleteByEmail(email)
                    return "YES"
                }
                !usersRepository.existsByEmail(email) -> {
                    usersRepository.save(
                        UserEntity(
                            email = email,
                            telegramId = telegramId,
                            password = generateHash(token)
                        )
                    )
                    tokensRepository.deleteByEmail(email)
                    return "YES"
                }
                else -> return "NO"
            }
        }
    }
}
