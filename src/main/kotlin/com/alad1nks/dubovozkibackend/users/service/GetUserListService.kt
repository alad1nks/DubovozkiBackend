package com.alad1nks.dubovozkibackend.users.service

import com.alad1nks.dubovozkibackend.users.UsersRepository
import com.alad1nks.dubovozkibackend.users.entities.UserListResponse
import org.springframework.stereotype.Service

@Service
class GetUserListService(
    val repository: UsersRepository
) {
    operator fun invoke(): UserListResponse {
        return UserListResponse.Valid(repository.findAll().toList())
    }
}
