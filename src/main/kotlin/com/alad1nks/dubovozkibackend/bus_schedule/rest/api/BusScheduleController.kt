package com.alad1nks.dubovozkibackend.bus_schedule.rest.api

import com.alad1nks.dubovozkibackend.bus_schedule.entities.BusEntity
import com.alad1nks.dubovozkibackend.bus_schedule.services.GetBusScheduleService
import com.alad1nks.dubovozkibackend.bus_schedule.services.UpdateBusScheduleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/router/bus-schedule")
class BusScheduleController(
    val getBusScheduleService: GetBusScheduleService,
    val updateBusScheduleService: UpdateBusScheduleService,
) {
    @GetMapping
    fun getBusSchedule() = getBusScheduleService.getBusSchedule()

    @PostMapping("/update")
    fun updateBusSchedule(
        @RequestBody busList: List<BusEntity>
    ) = updateBusScheduleService.updateBusSchedule(busList)
}
