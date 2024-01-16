package ru.easycode.zerotoheroandroidtdd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.easycode.zerotoheroandroidtdd.databinding.ItemTextBinding

class TextAdapter : ListAdapter<CharSequence, TextViewHolder>(DiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val binding = ItemTextBinding.inflate(LayoutInflater.from(parent.context))
        return TextViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        val text = currentList[position]
        holder.bind(text)
    }
}