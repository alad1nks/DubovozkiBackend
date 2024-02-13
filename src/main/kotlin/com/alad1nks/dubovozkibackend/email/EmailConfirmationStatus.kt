package com.alad1nks.dubovozkibackend.email

sealed interface EmailConfirmationStatus {
    val response: EmailVerificationResponse

    data object Confirmed : EmailConfirmationStatus {
        override val response
            get() = EmailVerificationResponse.EmailConfirmationResponse("EmailExists", 1)
    }

    data object OnConfirmation : EmailConfirmationStatus {
        override val response
            get() = EmailVerificationResponse.EmailConfirmationResponse("EmailOnConfirmation", 2)
    }

    data object NotConfirmed : EmailConfirmationStatus {
        override val response
            get() = EmailVerificationResponse.EmailConfirmationResponse("EmailNotConfirmed", 0)
    }
}
