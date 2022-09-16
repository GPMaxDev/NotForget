package com.dev.domain

class DeleteReminderItemUseCase (private val  reminderItemRepository: ReminderItemRepository) {

    fun deleteReminderItemByID (item: ReminderItem){
        reminderItemRepository.deleteReminderItemByID(item)
    }
}