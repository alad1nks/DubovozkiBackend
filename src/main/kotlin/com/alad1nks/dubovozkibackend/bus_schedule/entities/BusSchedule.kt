package com.alad1nks.dubovozkibackend.bus_schedule.entities

data class BusSchedule(
    val busEntityList: List<BusEntity>,
    val revision: Int
)