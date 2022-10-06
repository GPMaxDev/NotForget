package com.dev.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev.R

class ReminderItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    var tvName = view.findViewById<TextView>(R.id.nameRememberTextView)
    val tvDistance = view.findViewById<TextView>(R.id.distant)
    val tvCategory = view.findViewById<TextView>(R.id.categoryTextView)

}