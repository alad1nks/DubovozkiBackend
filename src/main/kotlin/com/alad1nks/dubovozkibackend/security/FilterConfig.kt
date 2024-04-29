package com.alad1nks.dubovozkibackend.security

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfig {

    @Bean
    fun registration(rateLimitingFilter: RateLimitingFilter): FilterRegistrationBean<RateLimitingFilter> {
        val registration = FilterRegistrationBean<RateLimitingFilter>()
        registration.filter = rateLimitingFilter
        registration.addUrlPatterns("/*")
        return registration
    }
}