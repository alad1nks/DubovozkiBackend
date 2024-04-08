package com.alad1nks.dubovozkibackend.registration.service

import com.alad1nks.dubovozkibackend.email.*
import com.alad1nks.dubovozkibackend.email.HseEmailValidator.validationStatus
import com.alad1nks.dubovozkibackend.registration.RegistrationTokensRepository
import com.alad1nks.dubovozkibackend.registration.entities.RegistrationEmailRequestBody
import com.alad1nks.dubovozkibackend.registration.entities.RegistrationTokenEntity
import com.alad1nks.dubovozkibackend.users.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

private const val EXPIRATION_TIME_MS: Long = 5 * 60 * 1000

@Service
class EmailVerificationUseCase(
    val tokensRepository: RegistrationTokensRepository,
    val usersRepository: UsersRepository
) {
    @Autowired
    lateinit var emailService: EmailService

    operator fun invoke(emailBody: RegistrationEmailRequestBody): EmailVerificationResponse {
        val validationStatus = emailBody.email.validationStatus()
        return when {
            validationStatus != EmailValidationStatus.Valid -> validationStatus.response
            tokensRepository.existsByEmail(emailBody.email) -> EmailConfirmationStatus.OnConfirmation.response
            usersRepository.existsByEmail(emailBody.email) -> {
                sendActivationCode(emailBody.email)
                EmailConfirmationStatus.Confirmed.response
            }
            else -> {
                sendActivationCode(emailBody.email)
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
                expiryDate = Calendar.getInstance().timeInMillis + EXPIRATION_TIME_MS
            )
        )
    }
}
