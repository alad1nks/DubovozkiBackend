package com.alad1nks.dubovozkibackend.random_coffee.service

import com.alad1nks.dubovozkibackend.random_coffee.entities.TelegramUsernameRequestBody
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class RandomCoffeeService(
    private val redisTemplate: RedisTemplate<String, Any>
) {
    private val usersKey = "USERS"
    private val unusedWordsKey = "WORDS"
    private val wordToUsersKey = "WORDS_PAIR"
    private val userToUserKey = "USERS_PAIR"

    fun join(telegramUsernameRequestBody: TelegramUsernameRequestBody) {
        redisTemplate.opsForSet().add(usersKey, telegramUsernameRequestBody.username)
    }

    fun addWords(words: List<String>) {
        words.forEach {
            redisTemplate.opsForSet().add(unusedWordsKey, it)
        }
    }

    fun generatePairs(): Map<String, Array<String>> {
        val setOperations = redisTemplate.opsForSet()
        val hashStringToStringArrayOperations = redisTemplate.opsForHash<String, Array<String>>()
        val hashStringToStringOperations = redisTemplate.opsForHash<String, String>()

        val users = setOperations.members(usersKey).orEmpty().shuffled().map { it.toString() }
        val words = setOperations.members(unusedWordsKey).orEmpty().toList().map { it.toString() }

        for (i in 0 until minOf(users.size / 2, words.size)) {
            hashStringToStringArrayOperations.put(wordToUsersKey, words[i], arrayOf(users[2 * i], users[2 * i + 1]))
            hashStringToStringOperations.put(userToUserKey, users[2 * i], users[2 * i + 1])
            hashStringToStringOperations.put(userToUserKey, users[2 * i + 1], users[2 * i])
            setOperations.members(usersKey)?.remove(users[2 * i])
            setOperations.members(usersKey)?.remove(users[2 * i + 1])
        }

        return hashStringToStringArrayOperations.entries(wordToUsersKey)
    }
}
