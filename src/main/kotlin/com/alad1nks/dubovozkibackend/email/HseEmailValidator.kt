package com.alad1nks.dubovozkibackend.email

import org.apache.commons.validator.routines.EmailValidator

object HseEmailValidator {
    fun String.validationStatus(): EmailValidationStatus {
        if (isNotEmail()) {
            return EmailValidationStatus.InvalidEmail
        }
        if (isNotHseEmail()) {
            return EmailValidationStatus.InvalidDomain
        }
        return EmailValidationStatus.Valid
    }

    private fun String.isNotEmail(): Boolean {
        return !EmailValidator.getInstance().isValid(this)
    }

    private fun String.isNotHseEmail(): Boolean {
        return !(this.endsWith("@edu.hse.ru") || this.endsWith("@hse.ru"))
    }
}