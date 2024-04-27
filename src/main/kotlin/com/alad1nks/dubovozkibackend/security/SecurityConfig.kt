package com.alad1nks.dubovozkibackend.security

import com.alad1nks.dubovozkibackend.users.entities.UserRole
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    val jwtAuthenticationFilter: JwtAuthenticationFilter
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf {
                it.disable()
            }
            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers(
                        "/router/bus-schedule/list",
                        "/router/bus-schedule/revision",
                        "/router/registration/verify/email",
                        "/router/registration/verify/token"
                    ).permitAll()
                    .requestMatchers(
                        "/router/random-coffee/join",
                    ).authenticated()
                    .anyRequest().hasAuthority(UserRole.ADMIN.name)
            }
            .httpBasic(Customizer.withDefaults())
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }
}
