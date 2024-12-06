package ru.easycode.zerotoheroandroidtdd.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.easycode.zerotoheroandroidtdd.core.model.ItemUi
import ru.easycode.zerotoheroandroidtdd.databinding.ItemTextBinding
import ru.easycode.zerotoheroandroidtdd.list.DeleteItemUi

class TextAdapter(
    private val deleteItemUi: DeleteItemUi
) : ListAdapter<ItemUi, TextViewHolder>(DiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val binding = ItemTextBinding.inflate(LayoutInflater.from(parent.context))
        return TextViewHolder(deleteItemUi, binding)
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        val text = currentList[position]
        holder.bind(text)
    }

}