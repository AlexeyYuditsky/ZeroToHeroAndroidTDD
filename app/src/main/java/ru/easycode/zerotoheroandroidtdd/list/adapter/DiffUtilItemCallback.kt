package ru.easycode.zerotoheroandroidtdd.list.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.easycode.zerotoheroandroidtdd.core.model.ItemUi

class DiffUtilItemCallback : DiffUtil.ItemCallback<ItemUi>() {

    override fun areItemsTheSame(oldItem: ItemUi, newItem: ItemUi): Boolean {
        return oldItem.areItemsSame(newItem)
    }

    override fun areContentsTheSame(oldItem: ItemUi, newItem: ItemUi): Boolean {
        return oldItem == newItem
    }

}