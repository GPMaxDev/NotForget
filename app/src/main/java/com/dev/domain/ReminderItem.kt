package com.dev.domain

data class ReminderItem(
    val id: Int,
    val name: String,
    val quantity: Int = 1,
    val latitude: Double,
    val longitude: Double,
    val distance_to: Int,
    val purchased: Boolean = false,
    val reminder_on: Boolean = false
)
