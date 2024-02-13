package com.alad1nks.dubovozkibackend.email

sealed interface EmailValidationStatus {
    val response: EmailVerificationResponse

    data object InvalidEmail : EmailValidationStatus {
        override val response
            get() = EmailVerificationResponse.EmailValidationResponse("InvalidEmail", 1)
    }

    data object InvalidDomain : EmailValidationStatus {
        override val response
            get() = EmailVerificationResponse.EmailValidationResponse("InvalidDomain", 2)
    }

    data object Valid : EmailValidationStatus {
        override val response
            get() = EmailVerificationResponse.EmailValidationResponse("Valid", 0)
    }
}
