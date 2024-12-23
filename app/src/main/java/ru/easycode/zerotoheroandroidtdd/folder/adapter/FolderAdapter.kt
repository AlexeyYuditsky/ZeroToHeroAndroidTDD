package ru.easycode.zerotoheroandroidtdd.folder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.easycode.zerotoheroandroidtdd.core.FolderUi
import ru.easycode.zerotoheroandroidtdd.databinding.ItemFolderBinding

class FolderAdapter : ListAdapter<FolderUi, FolderViewHolder>(DiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        val binding = ItemFolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FolderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        val text = currentList[position]
        holder.bind(text)
    }

}