package com.dev.data

import com.dev.domain.ReminderItem
import com.dev.domain.ReminderItemRepository

object ReminderItemRepositoryImpl: ReminderItemRepository {

    private val reminderList = mutableListOf<ReminderItem>()
    private var autoIncrementID = 0


    override fun addReminderItem(item: ReminderItem) {
        if (item.id == ReminderItem.UNDEFINED_ID) {
            item.id = autoIncrementID++
        }
        reminderList.add(item)
    }

    override fun deleteReminderItemByID(item: ReminderItem) {
        reminderList.remove(item)
    }

    override fun editReminderItemUSECase(item: ReminderItem) {
        val oldElement = getReminderItemById(item.id)
        reminderList.remove(oldElement)
        addReminderItem(item)
    }

    override fun getReminderItemList(): List<ReminderItem> {
        return reminderList.toList()
    }

    override fun getReminderItemById(id: Int): ReminderItem {
        return reminderList.find { it.id == id } ?: throw RuntimeException("Element with id $id not found")
    }


}