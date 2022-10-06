package com.dev.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dev.R
import com.dev.domain.ReminderItem

class ReminderItemListAdapter :
    ListAdapter<ReminderItem, ReminderItemViewHolder>(ReminderItemObjectDiffCallback()) {


        var onReminderItemClickListener: ((ReminderItem)-> Unit)? = null
        var onReminderItemShortClickListener: ((ReminderItem)-> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderItemViewHolder {
        val cellView = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.remember_cell_enebled
            VIEW_TYPE_DISABLED-> R.layout.remember_cell_disabled
            else-> throw RuntimeException("Unknown view type: $viewType")
        }

        val view = LayoutInflater.from(parent.context).inflate(cellView, parent, false)
        return ReminderItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ReminderItemViewHolder, position: Int) {
        val reminderItem = getItem(position)

        viewHolder.view.setOnLongClickListener {
           onReminderItemClickListener?.invoke (reminderItem)
                 true
        }

        viewHolder.view.setOnClickListener {
            onReminderItemShortClickListener?.invoke (reminderItem)

        }
        viewHolder.tvName.text = reminderItem.name
        viewHolder.tvDistance.text = reminderItem.distance_to.toString()
        viewHolder.tvCategory.text = reminderItem.category
    }

    override fun getItemViewType(position: Int): Int {
        val remindItem = getItem(position)

        return if (!remindItem.purchased) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }


    }

    companion object {
        const val VIEW_TYPE_ENABLED = 1
        const val VIEW_TYPE_DISABLED = 0
        const val MAX_POOL_SIZE = 15

    }
}