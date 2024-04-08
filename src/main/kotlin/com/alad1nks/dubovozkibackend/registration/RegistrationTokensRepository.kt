package com.alad1nks.dubovozkibackend.registration

import com.alad1nks.dubovozkibackend.registration.entities.RegistrationTokenEntity
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface RegistrationTokensRepository : CrudRepository<RegistrationTokenEntity, Long> {
    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String): RegistrationTokenEntity?

    @Modifying
    @Query("UPDATE REGISTRATION_TOKENS SET tries = tries - 1 WHERE id = :id")
    fun updateTokenTries(id: Long)

    @Modifying
    @Query("DELETE FROM REGISTRATION_TOKENS WHERE email = :email")
    fun deleteByEmail(email: String)

    @Modifying
    @Query("DELETE FROM REGISTRATION_TOKENS WHERE expiry_date < :expiryDate")
    fun deleteByExpiryDateBefore(expiryDate: Long)
}
