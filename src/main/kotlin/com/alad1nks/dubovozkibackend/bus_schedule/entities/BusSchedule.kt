package com.alad1nks.dubovozkibackend.bus_schedule.entities

data class BusSchedule(
    val busList: List<BusEntity>,
    val revision: Int
)
