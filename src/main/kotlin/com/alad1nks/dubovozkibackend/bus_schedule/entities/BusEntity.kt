package com.alad1nks.dubovozkibackend.bus_schedule.entities

import org.springframework.data.relational.core.mapping.Table

@Table("BUS_SCHEDULE")
data class BusEntity(
    val id: Int,
    val dayOfWeek: Int,
    val dayTime: Long,
    val dayTimeString: String,
    val direction: String,
    val station: String
)
