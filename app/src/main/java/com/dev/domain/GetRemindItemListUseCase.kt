package com.dev.domain

import androidx.lifecycle.LiveData

class GetRemindItemListUseCase(private val  reminderItemRepository: ReminderItemRepository) {

    fun getReminderItemList(): LiveData<List<ReminderItem>> {
        return reminderItemRepository.getReminderItemList()
    }
}