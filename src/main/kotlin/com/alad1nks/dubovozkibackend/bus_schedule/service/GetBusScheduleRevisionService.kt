package com.alad1nks.dubovozkibackend.bus_schedule.service

import com.alad1nks.dubovozkibackend.bus_schedule.entities.BusScheduleRevisionResponse
import com.alad1nks.dubovozkibackend.storage.Storage
import org.springframework.stereotype.Service

@Service
class GetBusScheduleRevisionService(
    val storage: Storage
) {
    operator fun invoke() = BusScheduleRevisionResponse(storage.getBusScheduleRevision())
}
