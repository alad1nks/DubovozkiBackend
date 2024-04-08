package com.alad1nks.dubovozkibackend.users.controller

import com.alad1nks.dubovozkibackend.users.entities.UserEntity
import com.alad1nks.dubovozkibackend.users.entities.UserListResponse
import com.alad1nks.dubovozkibackend.users.service.CreateUserService
import com.alad1nks.dubovozkibackend.users.service.GetUserByTelegramIdService
import com.alad1nks.dubovozkibackend.users.service.GetUserListService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/router/user")
class UsersController(
    val getUserListService: GetUserListService,
    val createUserService: CreateUserService,
    val getUserByTelegramIdService: GetUserByTelegramIdService
) {
    @GetMapping("/list")
    fun getUserList(): UserListResponse = getUserListService()

    @PostMapping("/create")
    fun createUser(
        @RequestBody userEntity: UserEntity
    ): String = createUserService(userEntity)

    @GetMapping("/telegram/{telegramId}")
    fun getUserByTelegramId(
        @PathVariable("telegramId") telegramId: String
    ): String = getUserByTelegramIdService(telegramId)
}
