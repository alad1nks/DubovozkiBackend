package com.alad1nks.dubovozkibackend.email

sealed interface EmailValidationStatus {
    fun response(): EmailVerificationResponse

    data object InvalidEmail : EmailValidationStatus {
        override fun response() = EmailVerificationResponse.EmailValidationResponse("InvalidEmail", 1)
    }

    data object InvalidDomain : EmailValidationStatus {
        override fun response() = EmailVerificationResponse.EmailValidationResponse("InvalidDomain", 2)
    }

    data object Valid : EmailValidationStatus {
        override fun response() = EmailVerificationResponse.EmailValidationResponse("Valid", 0)
    }
}