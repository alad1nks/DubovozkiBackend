package com.alad1nks.dubovozkibackend.email

sealed interface EmailConfirmationStatus {
    fun response(): EmailVerificationResponse

    data object Confirmed : EmailConfirmationStatus {
        override fun response() = EmailVerificationResponse.EmailConfirmationResponse("EmailExists", 1)
    }

    data object OnConfirmation : EmailConfirmationStatus {
        override fun response() = EmailVerificationResponse.EmailConfirmationResponse("EmailOnConfirmation", 2)
    }

    data object NotConfirmed : EmailConfirmationStatus {
        override fun response() = EmailVerificationResponse.EmailConfirmationResponse("EmailNotConfirmed", 0)
    }
}