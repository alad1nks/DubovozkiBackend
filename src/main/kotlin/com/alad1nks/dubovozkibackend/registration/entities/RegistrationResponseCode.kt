package com.alad1nks.dubovozkibackend.registration.entities

object RegistrationResponseCode {
    const val SUCCESS = 0
    const val EMAIL_DOES_NOT_EXIST = 1
    const val RETRIES_ARE_EXHAUSTED = 2
    const val INCORRECT_ACTIVATION_CODE = 3
}
