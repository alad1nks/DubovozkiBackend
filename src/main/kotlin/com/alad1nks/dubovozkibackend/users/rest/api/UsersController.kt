package com.alad1nks.dubovozkibackend.users.rest.api

import com.alad1nks.dubovozkibackend.users.entities.UserEntity
import com.alad1nks.dubovozkibackend.users.usecases.CreateUserUseCase
import com.alad1nks.dubovozkibackend.users.usecases.GetUserListUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/router/user")
class UsersController(
    val getUserListUseCase: GetUserListUseCase,
    val createUserUseCase: CreateUserUseCase
) {
    @GetMapping("/list")
    fun getUserList() = getUserListUseCase.getUserList()

    @PostMapping("/create")
    fun createUser(@RequestBody userEntity: UserEntity) {
        createUserUseCase.createUser(userEntity)
    }
}