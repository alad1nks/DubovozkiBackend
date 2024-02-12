package com.alad1nks.dubovozkibackend.email

sealed interface EmailVerificationResponse{
    data class EmailConfirmationResponse(
        val status: String,
        val error: Int
    ) : EmailVerificationResponse

    data class EmailValidationResponse(
        val status: String,
        val error: Int
    ) : EmailVerificationResponse
}