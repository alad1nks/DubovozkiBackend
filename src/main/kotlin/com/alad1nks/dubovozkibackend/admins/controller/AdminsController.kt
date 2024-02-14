package com.alad1nks.dubovozkibackend.admins.controller

import com.alad1nks.dubovozkibackend.admins.entities.AdminListResponse
import com.alad1nks.dubovozkibackend.admins.service.GetAdminListService
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/router/admin")
class AdminsController(
    val getAdminListService: GetAdminListService
) {
    @GetMapping("/list")
    fun getData(
        @CookieValue("email") email: String,
        @CookieValue("password") password: String
    ): AdminListResponse {
        return getAdminListService(email, password)
    }
}
