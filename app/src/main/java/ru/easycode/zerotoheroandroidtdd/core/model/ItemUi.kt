package ru.easycode.zerotoheroandroidtdd.core.model

import android.widget.TextView
import ru.easycode.zerotoheroandroidtdd.list.DeleteItemUi

data class ItemUi(
    private val id: Long,
    private val text: String
) {

    fun areItemsSame(other: ItemUi): Boolean = id == other.id

    fun delete(deleteItemUi: DeleteItemUi) = deleteItemUi.delete(id)

    fun show(textView: TextView) {
        textView.text = text
    }
}