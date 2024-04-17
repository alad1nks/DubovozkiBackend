package com.alad1nks.dubovozkibackend.email

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailServiceImpl : EmailService {
    @Autowired
    lateinit var emailSender: JavaMailSender

    override fun sendEmail(address: String, subject: String, message: String) {
        val simpleMailMessage = SimpleMailMessage()
        simpleMailMessage.from = "Дубовозки"
        simpleMailMessage.setTo(address)
        simpleMailMessage.subject = subject
        simpleMailMessage.text = message
        emailSender.send(simpleMailMessage)
    }
}
