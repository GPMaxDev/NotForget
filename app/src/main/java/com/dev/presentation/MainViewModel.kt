package com.dev.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dev.data.ReminderItemRepositoryImpl
import com.dev.domain.DeleteReminderItemUseCase
import com.dev.domain.EditReminderItemUseCase
import com.dev.domain.GetRemindItemListUseCase
import com.dev.domain.ReminderItem

class MainViewModel(application: Application) : AndroidViewModel(application)/* If dont need context mfy use extends of ViewModel*/ {

    private val repository = ReminderItemRepositoryImpl //!!! надо удалить потом или поменять єто нарушает модель не должно біть дата слоя во вью

    private val deleteReminderItemUseCase = DeleteReminderItemUseCase(repository)
    private val editReminderItemUseCase = EditReminderItemUseCase(repository)
    private val getRemindItemListUseCase = GetRemindItemListUseCase(repository)

    val reminderList = MutableLiveData<List<ReminderItem>>()

    fun getReminderList(){

        val listOfReminder = getRemindItemListUseCase.getReminderItemList()
        reminderList.postValue(listOfReminder)

    }

    fun deleteReminderItem(reminderItem: ReminderItem){
        deleteReminderItemUseCase.deleteReminderItemByID(reminderItem)
        getReminderList()
    }

    fun changeEnableState(reminderItem: ReminderItem){
        val newReminder = reminderItem.copy(purchased = !reminderItem.purchased)
        editReminderItemUseCase.editReminderItemUseCase(newReminder)
        getReminderList()
    }

}