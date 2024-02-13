package com.alad1nks.dubovozkibackend.registration.services

import com.alad1nks.dubovozkibackend.email.*
import com.alad1nks.dubovozkibackend.email.HseEmailValidator.validationStatus
import com.alad1nks.dubovozkibackend.registration.RegistrationTokensRepository
import com.alad1nks.dubovozkibackend.registration.entities.RegistrationTokenEntity
import com.alad1nks.dubovozkibackend.users.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class EmailVerificationUseCase(
    val tokensRepository: RegistrationTokensRepository,
    val usersRepository: UsersRepository
) {
    @Autowired
    lateinit var emailService: EmailService

    operator fun invoke(email: String): EmailVerificationResponse {
        val validationStatus = email.validationStatus()
        return when {
            validationStatus != EmailValidationStatus.Valid -> validationStatus.response
            tokensRepository.existsByEmail(email) -> EmailConfirmationStatus.OnConfirmation.response
            usersRepository.existsByEmail(email) -> {
                sendActivationCode(email)
                EmailConfirmationStatus.Confirmed.response
            }
            else -> {
                sendActivationCode(email)
                EmailConfirmationStatus.NotConfirmed.response
            }
        }
    }

    private fun sendActivationCode(email: String) {
        val token = TokenGenerator.verificationToken()
        emailService.sendEmail(email, "Код активации", token)
        tokensRepository.save(
            RegistrationTokenEntity(
                email = email,
                token = token,
                expiryDate = Calendar.getInstance().timeInMillis + 300000
            )
        )
    }
}
