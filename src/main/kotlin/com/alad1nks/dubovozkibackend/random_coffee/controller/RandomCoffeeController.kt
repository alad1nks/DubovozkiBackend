package com.alad1nks.dubovozkibackend.random_coffee.controller

import com.alad1nks.dubovozkibackend.random_coffee.entities.TelegramUsernameRequestBody
import com.alad1nks.dubovozkibackend.random_coffee.service.RandomCoffeeService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/router/random-coffee")
class RandomCoffeeController(
    private val service: RandomCoffeeService
) {
    @PostMapping("/generate-pairs")
    fun getPairs() = service.generatePairs()

    @PostMapping("/join")
    fun join(telegramUsernameRequestBody: TelegramUsernameRequestBody) {
        service.join(telegramUsernameRequestBody)
    }

    @PostMapping("/add-words")
    fun addWords(@RequestBody words: List<String>) = service.addWords(words)
}
