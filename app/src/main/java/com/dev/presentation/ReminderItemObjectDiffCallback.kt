package com.dev.presentation

import androidx.recyclerview.widget.DiffUtil
import com.dev.domain.ReminderItem

class ReminderItemObjectDiffCallback : DiffUtil.ItemCallback<ReminderItem>() {
    override fun areItemsTheSame(oldItem: ReminderItem, newItem: ReminderItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ReminderItem, newItem: ReminderItem): Boolean {
        return oldItem == newItem
    }


}