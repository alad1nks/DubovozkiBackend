package com.alad1nks.dubovozkibackend.bus_schedule.rest.api

import com.alad1nks.dubovozkibackend.bus_schedule.entities.BusEntity
import com.alad1nks.dubovozkibackend.bus_schedule.services.GetBusScheduleService
import com.alad1nks.dubovozkibackend.bus_schedule.services.UpdateBusScheduleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BusScheduleController(
    val getBusScheduleService: GetBusScheduleService,
    val updateBusScheduleService: UpdateBusScheduleService,
) {
    @GetMapping("/router/bus-schedule")
    fun getBusSchedule() = getBusScheduleService.getBusSchedule()

    @PostMapping("/router/bus-schedule/update")
    fun updateBusSchedule(
        @RequestBody busList: List<BusEntity>
    ) = updateBusScheduleService.updateBusSchedule(busList)
}
