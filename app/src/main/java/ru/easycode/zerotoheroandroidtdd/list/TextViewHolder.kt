package ru.easycode.zerotoheroandroidtdd.list

import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemTextBinding

class TextViewHolder(
    private val binding: ItemTextBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(text: CharSequence) = with(binding) {
        elementTextView.text = text.toString()
    }

}