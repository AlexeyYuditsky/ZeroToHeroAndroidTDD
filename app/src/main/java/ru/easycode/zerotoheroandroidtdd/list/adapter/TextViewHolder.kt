package ru.easycode.zerotoheroandroidtdd.list.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.easycode.zerotoheroandroidtdd.core.model.ItemUi
import ru.easycode.zerotoheroandroidtdd.databinding.ItemTextBinding
import ru.easycode.zerotoheroandroidtdd.list.DeleteItemUi

class TextViewHolder(
    private val deleteItem: DeleteItemUi,
    private val binding: ItemTextBinding
) : ViewHolder(binding.root) {

    fun bind(itemUi: ItemUi) {
        itemUi.show(binding.elementTextView)
        itemView.setOnClickListener { itemUi.delete(deleteItem) }
    }

}