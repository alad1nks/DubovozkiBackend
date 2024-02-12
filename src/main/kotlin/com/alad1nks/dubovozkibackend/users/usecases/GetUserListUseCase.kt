package com.alad1nks.dubovozkibackend.users.usecases

import com.alad1nks.dubovozkibackend.users.UsersRepository
import org.springframework.stereotype.Service

@Service
class GetUserListUseCase(
    val repository: UsersRepository
) {
    operator fun invoke() = repository.findAll().toList()
}