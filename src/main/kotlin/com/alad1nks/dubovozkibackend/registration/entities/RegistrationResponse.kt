package com.alad1nks.dubovozkibackend.registration.entities

import com.alad1nks.dubovozkibackend.registration.entities.RegistrationResponseCode.EMAIL_DOES_NOT_EXIST
import com.alad1nks.dubovozkibackend.registration.entities.RegistrationResponseCode.INCORRECT_ACTIVATION_CODE
import com.alad1nks.dubovozkibackend.registration.entities.RegistrationResponseCode.RETRIES_ARE_EXHAUSTED
import com.alad1nks.dubovozkibackend.registration.entities.RegistrationResponseCode.SUCCESS

sealed interface RegistrationResponse {
    fun response(jwt: String? = null): TokenVerificationResponse

    data object EmailDoesNotExist : RegistrationResponse {
        override fun response(jwt: String?): TokenVerificationResponse =
            TokenVerificationResponse(EMAIL_DOES_NOT_EXIST)
    }

    data object RetriesAreExhausted : RegistrationResponse {
        override fun response(jwt: String?): TokenVerificationResponse =
            TokenVerificationResponse(RETRIES_ARE_EXHAUSTED)

    }

    data object IncorrectActivationCode : RegistrationResponse {
        override fun response(jwt: String?): TokenVerificationResponse =
            TokenVerificationResponse(INCORRECT_ACTIVATION_CODE)
    }

    data object Success : RegistrationResponse {
        override fun response(jwt: String?): TokenVerificationResponse =
            TokenVerificationResponse(SUCCESS, jwt)
    }
}
