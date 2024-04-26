package com.alad1nks.dubovozkibackend.random_coffee.controller

import com.alad1nks.dubovozkibackend.random_coffee.service.RandomCoffeeService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/router/random-coffee")
class RandomCoffeeController(
    private val service: RandomCoffeeService
) {
    @PostMapping("/generate-pairs")
    fun getPairs() = service.generatePairs()

    @PostMapping("/add-user")
    fun addUser(@RequestBody user: String) = service.addUser(user)

    @PostMapping("/add-words")
    fun addWords(@RequestBody words: List<String>) = service.addWords(words)
}
