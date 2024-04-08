package com.alad1nks.dubovozkibackend.users.service

import com.alad1nks.dubovozkibackend.users.UsersRepository
import org.springframework.stereotype.Service

@Service
class GetUserByTelegramIdService(
    val repository: UsersRepository
) {
    operator fun invoke(telegramId: String): String {
        if (repository.existsByTelegramId(telegramId)) {
            return "YES"
        }
        return "NO"
    }
}