package com.alad1nks.dubovozkibackend.bus_schedule.service

import com.alad1nks.dubovozkibackend.bus_schedule.BusScheduleRepository
import com.alad1nks.dubovozkibackend.bus_schedule.entities.BusScheduleResponse
import org.springframework.stereotype.Service

@Service
class GetBusScheduleService(
    val repository: BusScheduleRepository
) {
    operator fun invoke() = BusScheduleResponse(repository.findAll().toList())
}
