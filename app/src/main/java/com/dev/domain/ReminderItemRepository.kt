package com.dev.domain

interface ReminderItemRepository {

    fun addReminderItem(item: ReminderItem)

    fun deleteReminderItemByID (item: ReminderItem)

    fun editReminderItemUSECase(item: ReminderItem)

    fun getReminderItemList(): List<ReminderItem>

    fun getReminderItemById (id: Int): ReminderItem
}