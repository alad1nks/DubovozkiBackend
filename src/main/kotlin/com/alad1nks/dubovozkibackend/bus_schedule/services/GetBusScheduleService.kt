package com.alad1nks.dubovozkibackend.bus_schedule.services

import com.alad1nks.dubovozkibackend.bus_schedule.BusScheduleRepository
import com.alad1nks.dubovozkibackend.bus_schedule.entities.BusSchedule
import com.alad1nks.dubovozkibackend.storage.Storage
import org.springframework.stereotype.Service

@Service
class GetBusScheduleService(
    val repository: BusScheduleRepository,
    val storage: Storage
) {
    fun getBusSchedule() = BusSchedule(
        busEntityList = repository.findAll().toList(),
        revision = storage.getBusScheduleRevision()
    )
}