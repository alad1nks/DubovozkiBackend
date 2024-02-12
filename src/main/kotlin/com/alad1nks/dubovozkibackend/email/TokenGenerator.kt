package com.alad1nks.dubovozkibackend.email

import java.security.SecureRandom

object TokenGenerator {
    private val random: SecureRandom = SecureRandom()

    fun verificationToken(): String {
        val sb = StringBuilder()
        for (i in 0 until 6) {
            sb.append(random.nextInt(10))
        }
        return sb.toString()
    }

}