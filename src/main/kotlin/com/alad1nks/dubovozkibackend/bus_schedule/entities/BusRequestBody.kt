package com.alad1nks.dubovozkibackend.bus_schedule.entities

data class BusRequestBody(
    val dayOfWeek: Int,
    val dayTime: String,
    val direction: String,
    val station: String
)