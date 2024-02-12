package com.alad1nks.dubovozkibackend.email

interface EmailService {
    fun sendEmail(address: String, subject: String, message: String)
}