package com.alad1nks.dubovozkibackend.users.controller

import com.alad1nks.dubovozkibackend.users.entities.UserEntity
import com.alad1nks.dubovozkibackend.users.service.CreateUserService
import com.alad1nks.dubovozkibackend.users.service.GetUserListService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/router/user")
class UsersController(
    val getUserListService: GetUserListService,
    val createUserService: CreateUserService
) {
    @GetMapping("/list")
    fun getUserList(): List<UserEntity> {
        return getUserListService()
    }

    @PostMapping("/create")
    fun createUser(
        @RequestBody userEntity: UserEntity
    ): String {
        return createUserService(userEntity)
    }
}
