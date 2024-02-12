package com.alad1nks.dubovozkibackend.registration.services

import com.alad1nks.dubovozkibackend.registration.RegistrationTokensRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*

@Component
class TokenCleanupService(val tokensRepository: RegistrationTokensRepository) {

    @Scheduled(fixedRate = 30000)
    fun deleteExpiredTokens() {
        val currentTime = Calendar.getInstance().timeInMillis
        tokensRepository.deleteByExpiryDateBefore(currentTime)
    }
}
