package com.alad1nks.dubovozkibackend.registration

import com.alad1nks.dubovozkibackend.registration.entities.RegistrationTokenEntity
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RegistrationTokensRepository : CrudRepository<RegistrationTokenEntity, Long> {

    fun existsByEmail(email: String): Boolean
    fun existsByEmailAndToken(email: String, token: String): Boolean

    @Modifying
    @Query("DELETE FROM REGISTRATION_TOKENS WHERE email = :email")
    fun deleteByEmail(email: String)

    @Modifying
    @Query("DELETE FROM REGISTRATION_TOKENS WHERE expiry_date < :expiryDate")
    fun deleteByExpiryDateBefore(expiryDate: Long)
}
