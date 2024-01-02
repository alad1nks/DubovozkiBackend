package com.alad1nks.dubovozkibackend.bus_schedule.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.sql.Time

@Table("BUS_SCHEDULE")
data class BusEntity(
    @Id
    var id: Int?,
    val dayOfWeek: Int,
    val dayTime: Time,
    val direction: String,
    val station: String
)