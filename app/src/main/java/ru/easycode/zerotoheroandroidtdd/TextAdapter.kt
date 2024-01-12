package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemTextBinding

class TextAdapter : RecyclerView.Adapter<TextViewHolder>() {

    private val list = arrayListOf<CharSequence>()

    fun save(bundle: Bundle) {
        bundle.putCharSequenceArrayList(KEY, list)
    }

    fun restore(bundle: Bundle) {
        val savedList = bundle.getCharSequenceArrayList(KEY) ?: return
        list.addAll(savedList)
    }

    fun update(text: CharSequence) {
        list.add(text)
        notifyItemInserted(list.lastIndex)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val binding = ItemTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TextViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    private companion object {
        const val KEY = "key"
    }
}