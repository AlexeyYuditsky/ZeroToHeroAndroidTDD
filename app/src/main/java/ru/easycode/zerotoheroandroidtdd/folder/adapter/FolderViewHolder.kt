package ru.easycode.zerotoheroandroidtdd.folder.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.easycode.zerotoheroandroidtdd.core.FolderUi
import ru.easycode.zerotoheroandroidtdd.databinding.ItemFolderBinding

class FolderViewHolder(
    private val binding: ItemFolderBinding
) : ViewHolder(binding.root) {

    fun bind(folderUi: FolderUi) = with(binding) {
        folderUi.show(folderTitleTextView, folderCountTextView)
        //itemView.setOnClickListener { itemUi.delete(deleteItem) }
    }

}