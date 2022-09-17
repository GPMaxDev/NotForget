package com.dev.domain

data class ReminderItem(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val distance_to: Int,
    val purchased: Boolean = false,
    val reminder_on: Boolean = true,
    var id: Int = UNDEFINED_ID,
    var quantity: Int = DEFAULT_QUANtITY,

) {

    companion object {
        const val UNDEFINED_ID = -1
        const val DEFAULT_QUANtITY = 1

    }
}
