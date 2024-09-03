package ru.easycode.zerotoheroandroidtdd.list

import androidx.recyclerview.widget.DiffUtil

class DiffUtilItemCallback : DiffUtil.ItemCallback<CharSequence>() {

    override fun areItemsTheSame(oldItem: CharSequence, newItem: CharSequence): Boolean {
        return oldItem.contentEquals(newItem)
    }

    override fun areContentsTheSame(oldItem: CharSequence, newItem: CharSequence): Boolean {
        return oldItem.contentEquals(newItem)
    }
}