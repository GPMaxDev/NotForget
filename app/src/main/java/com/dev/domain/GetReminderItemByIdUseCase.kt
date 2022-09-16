package com.dev.domain

class GetReminderItemByIdUseCase(private val  reminderItemRepository: ReminderItemRepository) {

    fun getReminderItemById (id: Int): ReminderItem {
        return reminderItemRepository.getReminderItemById(id)
    }
}