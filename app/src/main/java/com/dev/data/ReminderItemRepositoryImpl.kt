package com.dev.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev.domain.ReminderItem
import com.dev.domain.ReminderItemRepository
import java.util.Random

object ReminderItemRepositoryImpl: ReminderItemRepository {

    private val remListLiveData = MutableLiveData<List<ReminderItem>>()
    private val reminderList =  sortedSetOf<ReminderItem>({ o1, o2 -> o1.id.compareTo(o2.id)})

    private var autoIncrementID = 0


    init {
        for (i in 0 until 100){
            val rem = ReminderItem("Name $i", 0.0,0.0, 0, "food",kotlin.random.Random.nextBoolean(), true)
            addReminderItem(rem)
        }
    }


    override fun addReminderItem(item: ReminderItem) {
        if (item.id == ReminderItem.UNDEFINED_ID) {
            item.id = autoIncrementID++
        }
        reminderList.add(item)
        updateList()
    }

    override fun deleteReminderItemByID(item: ReminderItem) {
        reminderList.remove(item)
        updateList()
    }

    override fun editReminderItemUseCase(item: ReminderItem) {
        val oldElement = getReminderItemById(item.id)
        reminderList.remove(oldElement)
        addReminderItem(item)
    }

    override fun getReminderItemList(): LiveData<List<ReminderItem>> {
        return remListLiveData
    }

    override fun getReminderItemById(id: Int): ReminderItem {
        return reminderList.find { it.id == id } ?: throw RuntimeException("Element with id $id not found")
    }

    private fun updateList(){
        remListLiveData.postValue(reminderList.toList())

    }


}