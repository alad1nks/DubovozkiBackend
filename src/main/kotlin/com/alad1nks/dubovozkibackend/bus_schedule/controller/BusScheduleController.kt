package com.alad1nks.dubovozkibackend.bus_schedule.controller

import com.alad1nks.dubovozkibackend.bus_schedule.entities.BusEntity
import com.alad1nks.dubovozkibackend.bus_schedule.entities.BusScheduleResponse
import com.alad1nks.dubovozkibackend.bus_schedule.entities.BusScheduleRevisionResponse
import com.alad1nks.dubovozkibackend.bus_schedule.service.GetBusScheduleRevisionService
import com.alad1nks.dubovozkibackend.bus_schedule.service.GetBusScheduleService
import com.alad1nks.dubovozkibackend.bus_schedule.service.UpdateBusScheduleService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/router/bus-schedule")
class BusScheduleController(
    val getBusScheduleService: GetBusScheduleService,
    val getBusScheduleRevisionService: GetBusScheduleRevisionService,
    val updateBusScheduleService: UpdateBusScheduleService
) {
    @GetMapping("/list")
    fun getBusSchedule(): BusScheduleResponse = getBusScheduleService()

    @GetMapping("/revision")
    fun getBusScheduleRevision(): BusScheduleRevisionResponse = getBusScheduleRevisionService()

    @PostMapping("/update")
    fun updateBusSchedule(
        @RequestBody busList: List<BusEntity>
    ): String = updateBusScheduleService(busList)
}
