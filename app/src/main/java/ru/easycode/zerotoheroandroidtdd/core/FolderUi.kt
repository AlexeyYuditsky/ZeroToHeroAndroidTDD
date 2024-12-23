package ru.easycode.zerotoheroandroidtdd.core

import android.widget.TextView

data class FolderUi(
    private val id: Long,
    private val title: String,
    private val notesCount: Int
) {

    fun areItemsSame(newItem: FolderUi): Boolean = newItem.id == id

    fun show(
        titleTextView: TextView,
        countTextView: TextView
    ) {
        titleTextView.text = title
        countTextView.text = "$notesCount"
    }

}