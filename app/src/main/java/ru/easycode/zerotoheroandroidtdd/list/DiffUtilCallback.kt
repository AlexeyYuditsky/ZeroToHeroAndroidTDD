package ru.easycode.zerotoheroandroidtdd.list

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}