package com.alad1nks.dubovozkibackend.users.service

import com.alad1nks.dubovozkibackend.admins.AdminsRepository
import com.alad1nks.dubovozkibackend.users.UsersRepository
import com.alad1nks.dubovozkibackend.users.entities.UserListResponse
import org.springframework.stereotype.Service

@Service
class GetUserListService(
    val repository: UsersRepository,
    val adminsRepository: AdminsRepository
) {
    operator fun invoke(email: String, password: String): UserListResponse {
        return if (adminsRepository.existsByEmailAndPassword(email, password)) {
            UserListResponse.Valid(repository.findAll().toList())
        } else {
            UserListResponse.Invalid
        }
    }
}
