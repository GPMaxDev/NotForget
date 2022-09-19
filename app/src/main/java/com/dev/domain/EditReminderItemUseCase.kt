package com.dev.domain

class EditReminderItemUseCase (private val  reminderItemRepository: ReminderItemRepository) {

    fun editReminderItemUseCase(item: ReminderItem) {
       reminderItemRepository.editReminderItemUseCase(item)
    }
}