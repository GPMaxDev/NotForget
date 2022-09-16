package com.dev.domain

class EditReminderItemUseCase (private val  reminderItemRepository: ReminderItemRepository) {

    fun editReminderItemUSECase(item: ReminderItem) {
       reminderItemRepository.editReminderItemUSECase(item)
    }
}