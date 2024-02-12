package com.alad1nks.dubovozkibackend.registration.rest.api

import com.alad1nks.dubovozkibackend.registration.entities.RegistrationTokenRequestBody
import com.alad1nks.dubovozkibackend.registration.services.EmailVerificationService
import com.alad1nks.dubovozkibackend.registration.services.TokenVerificationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/router/registration")
class RegistrationController(
    val emailVerificationService: EmailVerificationService,
    val tokenVerificationService: TokenVerificationService
) {

    @GetMapping("/verify/email/{user-email}")
    @ResponseBody
    fun verifyEmail(
        @PathVariable("user-email") email: String
    ) = emailVerificationService.verify(email)

    @PostMapping("/verify/token")
    @ResponseBody
    fun verifyToken(
        @RequestBody tokenBody: RegistrationTokenRequestBody
    ) = tokenVerificationService.verify(tokenBody)
}