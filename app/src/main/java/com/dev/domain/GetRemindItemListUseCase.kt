package com.dev.domain

class GetRemindItemListUseCase(private val  reminderItemRepository: ReminderItemRepository) {

    fun getReminderItemList(): List<ReminderItem>{
        return reminderItemRepository.getReminderItemList()
    }
}