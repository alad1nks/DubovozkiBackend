package com.alad1nks.dubovozkibackend.security

import java.security.SecureRandom
import java.security.spec.KeySpec
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

object SecurePasswordHashGenerator {
    private const val ALGORITHM = "PBKDF2WithHmacSHA512"
    private const val ITERATIONS = 120_000
    private const val KEY_LENGTH = 256
    private const val SECRET = "SomeRandomSecret"

    fun generateHash(password: String, salt: String = defaultSalt()): String {
        val combinedSalt = "$salt$SECRET".toByteArray()
        val factory: SecretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM)
        val spec: KeySpec = PBEKeySpec(password.toCharArray(), combinedSalt, ITERATIONS, KEY_LENGTH)
        val key: SecretKey = factory.generateSecret(spec)
        val hash: ByteArray = key.encoded
        return hash.toHexString()
    }

    private fun defaultSalt(): String = generateRandomSalt().toHexString()

    private fun generateRandomSalt(): ByteArray {
        val random = SecureRandom()
        val salt = ByteArray(16)
        random.nextBytes(salt)
        return salt
    }

    private fun ByteArray.toHexString(): String = joinToString("") { "%02x".format(it) }
}