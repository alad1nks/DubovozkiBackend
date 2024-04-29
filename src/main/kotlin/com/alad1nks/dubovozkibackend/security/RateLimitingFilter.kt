package com.alad1nks.dubovozkibackend.security

import jakarta.servlet.*
import jakarta.servlet.FilterConfig
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

private const val REQUEST_LIMIT = 100
private const val TIME_WINDOW = 10000L

@Component
class RateLimitingFilter : Filter {

    private val requestCounts = ConcurrentHashMap<String, AtomicInteger>()
    private val scheduler = Executors.newScheduledThreadPool(1)

    override fun init(filterConfig: FilterConfig?) {}

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpRequest = request as HttpServletRequest
        val httpResponse = response as HttpServletResponse
        val clientIp = httpRequest.remoteAddr

        val requests = requestCounts.computeIfAbsent(clientIp) { AtomicInteger(0) }

        if (requests.incrementAndGet() > REQUEST_LIMIT) {
            httpResponse.status = 429
            httpResponse.writer.println("Rate limit exceeded")
            return
        }

        scheduler.schedule({
            requests.set(0)
        }, TIME_WINDOW, TimeUnit.MILLISECONDS)

        chain.doFilter(request, response)
    }

    override fun destroy() {
        requestCounts.clear()
        scheduler.shutdown()
    }
}