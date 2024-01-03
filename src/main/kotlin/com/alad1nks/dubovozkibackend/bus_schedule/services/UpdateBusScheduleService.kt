package com.alad1nks.dubovozkibackend.bus_schedule.services

import com.alad1nks.dubovozkibackend.bus_schedule.BusScheduleRepository
import com.alad1nks.dubovozkibackend.bus_schedule.entities.BusEntity
import com.alad1nks.dubovozkibackend.bus_schedule.entities.BusRequestBody
import com.alad1nks.dubovozkibackend.storage.Storage

import org.springframework.stereotype.Service

@Service
class UpdateBusScheduleService(
    val repository: BusScheduleRepository,
    val storage: Storage,
) {
    fun updateBusSchedule(busRequestBodyList: List<BusRequestBody>): String {
        storage.updateBusScheduleRevision()
        repository.deleteAll()
        repository.saveAll(busRequestBodyList.toEntity())
        return "OK"
    }

    private fun List<BusRequestBody>.toEntity(): List<BusEntity> {
        return this.map {
            it.toEntity()
        }
    }

    private fun BusRequestBody.toEntity(): BusEntity = BusEntity(
        id = null,
        dayOfWeek = dayOfWeek,
        dayTime = dayTime.toMilliseconds(),
        dayTimeString = dayTime,
        direction = direction,
        station = station
    )

    private fun String.toMilliseconds(): Long {
        val vars = this.split(':').map { it.toLong() }
        return vars[0] * 3600000 + vars[1] * 60000
    }
}