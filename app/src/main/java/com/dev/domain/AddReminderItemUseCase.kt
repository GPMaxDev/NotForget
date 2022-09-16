package com.dev.domain

class AddReminderItemUseCase(private val  reminderItemRepository: ReminderItemRepository) {

    fun addReminderItem(item: ReminderItem) {
        reminderItemRepository.addReminderItem(item)
    }
}