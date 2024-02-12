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
class EmailVerificationService(
    val tokensRepository: RegistrationTokensRepository,
    val usersRepository: UsersRepository
) {
    @Autowired
    lateinit var emailService: EmailService

    fun verify(email: String): EmailVerificationResponse {
        val validationStatus = email.validationStatus()
        return when {
            validationStatus != EmailValidationStatus.Valid -> validationStatus.response()
            tokensRepository.existsByEmail(email) -> EmailConfirmationStatus.OnConfirmation.response()
            usersRepository.existsByEmail(email) -> EmailConfirmationStatus.Confirmed.response()
            else -> {
                val token = TokenGenerator.verificationToken()
                emailService.sendEmail(email, "Код активации", token)
                tokensRepository.save(
                    RegistrationTokenEntity(
                        email = email,
                        token = token,
                        expiryDate = Calendar.getInstance().timeInMillis + 300000
                    )
                )
                EmailConfirmationStatus.NotConfirmed.response()
            }
        }
    }
}