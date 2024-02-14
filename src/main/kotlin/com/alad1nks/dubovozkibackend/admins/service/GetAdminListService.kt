package com.alad1nks.dubovozkibackend.admins.service

import com.alad1nks.dubovozkibackend.admins.AdminsRepository
import com.alad1nks.dubovozkibackend.admins.entities.AdminListResponse
import org.springframework.stereotype.Service

@Service
class GetAdminListService(
    val repository: AdminsRepository
) {
    operator fun invoke(email: String, password: String) : AdminListResponse {
        return if (repository.existsByEmailAndPassword(email, password)) {
            AdminListResponse.Valid(repository.findAll().toList())
        } else {
            AdminListResponse.Invalid
        }
    }
}
