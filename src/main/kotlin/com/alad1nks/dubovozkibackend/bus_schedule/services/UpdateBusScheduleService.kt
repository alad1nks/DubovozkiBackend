package com.alad1nks.dubovozkibackend.bus_schedule.services

import com.alad1nks.dubovozkibackend.bus_schedule.BusScheduleRepository
import com.alad1nks.dubovozkibackend.bus_schedule.entities.BusEntity
import com.alad1nks.dubovozkibackend.storage.Storage

import org.springframework.stereotype.Service

@Service
class UpdateBusScheduleService(
    val repository: BusScheduleRepository,
    val storage: Storage,
) {
    operator fun invoke(busRequestBodyList: List<BusEntity>): String {
        storage.updateBusScheduleRevision()
        repository.deleteAll()
        repository.saveAll(busRequestBodyList)
        return "OK"
    }
}
