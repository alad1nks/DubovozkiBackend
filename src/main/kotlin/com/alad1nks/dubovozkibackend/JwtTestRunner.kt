package com.alad1nks.dubovozkibackend

import com.alad1nks.dubovozkibackend.security.JwtTokenUtil
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class JwtTestRunner(
    val jwtUtil: JwtTokenUtil
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        println("Loaded JWT Secret: ${jwtUtil.jwtSecret}")
    }
}
