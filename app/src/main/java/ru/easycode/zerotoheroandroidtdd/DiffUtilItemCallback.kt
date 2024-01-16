package ru.easycode.zerotoheroandroidtdd

import androidx.recyclerview.widget.DiffUtil

class DiffUtilItemCallback : DiffUtil.ItemCallback<CharSequence>() {

    override fun areItemsTheSame(oldItem: CharSequence, newItem: CharSequence): Boolean {
        return oldItem == newItem
    }


    override fun areContentsTheSame(oldItem: CharSequence, newItem: CharSequence): Boolean {
        return oldItem == newItem
    }
}

fun main() {
    val text1: CharSequence = "123"
    val text2: CharSequence = "124"

    println(text1 == text2)
}