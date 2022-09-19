package com.dev.domain

import androidx.lifecycle.LiveData

interface ReminderItemRepository {

    fun addReminderItem(item: ReminderItem)

    fun deleteReminderItemByID (item: ReminderItem)

    fun editReminderItemUseCase(item: ReminderItem)

    fun getReminderItemList(): LiveData<List<ReminderItem>>

    fun getReminderItemById (id: Int): ReminderItem
}