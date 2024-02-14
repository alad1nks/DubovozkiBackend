package com.alad1nks.dubovozkibackend.users.controller

import com.alad1nks.dubovozkibackend.users.entities.UserEntity
import com.alad1nks.dubovozkibackend.users.entities.UserListResponse
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
    fun getUserList(
        @CookieValue("email") email: String,
        @CookieValue("password") password: String
    ) : UserListResponse = getUserListService(email, password)

    @PostMapping("/create")
    fun createUser(
        @RequestBody userEntity: UserEntity,
        @CookieValue("email") email: String,
        @CookieValue("password") password: String
    ) : String {
        return createUserService(userEntity, email, password)
    }
}
