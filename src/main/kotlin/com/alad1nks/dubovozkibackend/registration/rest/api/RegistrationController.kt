package com.alad1nks.dubovozkibackend.registration.rest.api

import com.alad1nks.dubovozkibackend.registration.entities.RegistrationTokenRequestBody
import com.alad1nks.dubovozkibackend.registration.services.EmailVerificationUseCase
import com.alad1nks.dubovozkibackend.registration.services.TokenVerificationUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/router/registration")
class RegistrationController(
    val emailVerificationUseCase: EmailVerificationUseCase,
    val tokenVerificationUseCase: TokenVerificationUseCase
) {
    @GetMapping("/verify/email/{user-email}")
    @ResponseBody
    fun verifyEmail(
        @PathVariable("user-email") email: String
    ) = emailVerificationUseCase(email)

    @PostMapping("/verify/token")
    @ResponseBody
    fun verifyToken(
        @RequestBody tokenBody: RegistrationTokenRequestBody
    ) = tokenVerificationUseCase(tokenBody)
}