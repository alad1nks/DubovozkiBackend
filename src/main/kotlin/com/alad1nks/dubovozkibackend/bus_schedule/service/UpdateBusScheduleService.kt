package com.alad1nks.dubovozkibackend.bus_schedule.service

import com.alad1nks.dubovozkibackend.admins.AdminsRepository
import com.alad1nks.dubovozkibackend.bus_schedule.BusScheduleRepository
import com.alad1nks.dubovozkibackend.bus_schedule.entities.BusEntity
import com.alad1nks.dubovozkibackend.storage.Storage

import org.springframework.stereotype.Service

@Service
class UpdateBusScheduleService(
    val adminsRepository: AdminsRepository,
    val repository: BusScheduleRepository,
    val storage: Storage
) {
    operator fun invoke(busRequestBodyList: List<BusEntity>, email: String, password: String): String {
        if (adminsRepository.existsByEmailAndPassword(email, password)) {
            storage.updateBusScheduleRevision()
            repository.deleteAll()
            repository.saveAll(busRequestBodyList)
            return "OK"
        }
        return "ERROR"
    }
}
