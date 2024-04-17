package com.alad1nks.dubovozkibackend.bus_schedule

import com.alad1nks.dubovozkibackend.bus_schedule.entities.BusEntity
import org.springframework.data.repository.CrudRepository

interface BusScheduleRepository : CrudRepository<BusEntity, String>
