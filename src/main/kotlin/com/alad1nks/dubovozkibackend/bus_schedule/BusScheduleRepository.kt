package com.alad1nks.dubovozkibackend.bus_schedule

import com.alad1nks.dubovozkibackend.bus_schedule.entities.BusEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BusScheduleRepository : CrudRepository<BusEntity, Long>
