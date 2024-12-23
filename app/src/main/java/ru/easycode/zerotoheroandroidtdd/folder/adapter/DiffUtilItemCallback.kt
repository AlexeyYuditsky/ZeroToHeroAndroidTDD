package ru.easycode.zerotoheroandroidtdd.folder.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.easycode.zerotoheroandroidtdd.core.FolderUi

class DiffUtilItemCallback : DiffUtil.ItemCallback<FolderUi>() {

    override fun areItemsTheSame(oldItem: FolderUi, newItem: FolderUi): Boolean {
        return oldItem.areItemsSame(newItem)
    }

    override fun areContentsTheSame(oldItem: FolderUi, newItem: FolderUi): Boolean {
        return oldItem == newItem
    }

}