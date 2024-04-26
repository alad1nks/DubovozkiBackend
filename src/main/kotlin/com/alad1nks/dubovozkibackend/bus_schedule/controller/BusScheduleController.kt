package com.alad1nks.dubovozkibackend.bus_schedule.controller

import com.alad1nks.dubovozkibackend.bus_schedule.entities.BusEntity
import com.alad1nks.dubovozkibackend.bus_schedule.service.GetBusScheduleService
import com.alad1nks.dubovozkibackend.bus_schedule.service.UpdateBusScheduleService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/router/bus-schedule")
class BusScheduleController(
    val getBusScheduleService: GetBusScheduleService,
    val updateBusScheduleService: UpdateBusScheduleService
) {
    @GetMapping
    fun getBusSchedule() = getBusScheduleService()

    @PostMapping("/update")
    fun updateBusSchedule(
        @RequestBody busList: List<BusEntity>
    ) = updateBusScheduleService(busList)
}
