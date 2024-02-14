package com.alad1nks.dubovozkibackend.bus_schedule.service

import com.alad1nks.dubovozkibackend.bus_schedule.BusScheduleRepository
import com.alad1nks.dubovozkibackend.bus_schedule.entities.BusSchedule
import com.alad1nks.dubovozkibackend.storage.Storage
import org.springframework.stereotype.Service

@Service
class GetBusScheduleService(
    val repository: BusScheduleRepository,
    val storage: Storage
) {
    operator fun invoke() = BusSchedule(
        busList = repository.findAll().toList(),
        revision = storage.getBusScheduleRevision()
    )
}
