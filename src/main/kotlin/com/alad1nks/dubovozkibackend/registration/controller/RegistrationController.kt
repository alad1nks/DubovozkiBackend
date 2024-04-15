package com.alad1nks.dubovozkibackend.registration.controller

import com.alad1nks.dubovozkibackend.email.EmailVerificationResponse
import com.alad1nks.dubovozkibackend.registration.entities.RegistrationEmailRequestBody
import com.alad1nks.dubovozkibackend.registration.entities.RegistrationTokenRequestBody
import com.alad1nks.dubovozkibackend.registration.entities.TokenVerificationResponse
import com.alad1nks.dubovozkibackend.registration.service.EmailVerificationUseCase
import com.alad1nks.dubovozkibackend.registration.service.TokenVerificationUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/router/registration")
class RegistrationController(
    val emailVerificationUseCase: EmailVerificationUseCase,
    val tokenVerificationUseCase: TokenVerificationUseCase
) {
    @PostMapping("/verify/email")
    @ResponseBody
    fun verifyEmail(@RequestBody emailBody: RegistrationEmailRequestBody): EmailVerificationResponse =
        emailVerificationUseCase(emailBody)

    @PostMapping("/verify/token")
    @ResponseBody
    fun verifyToken(@RequestBody tokenBody: RegistrationTokenRequestBody): TokenVerificationResponse =
        tokenVerificationUseCase(tokenBody)
}
