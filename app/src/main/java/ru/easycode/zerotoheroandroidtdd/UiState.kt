package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.TextView
import java.io.Serializable

interface UiState : Serializable {

    fun apply(textView: TextView, buttonDecrement: Button, buttonIncrement: Button) = Unit

    abstract class Abstract(
        private val text: String
    ) : UiState {
        override fun apply(textView: TextView, buttonDecrement: Button, buttonIncrement: Button) {
            textView.text = text
        }
    }

    data class Base(
        private val text: String
    ) : Abstract(text) {
        override fun apply(textView: TextView, buttonDecrement: Button, buttonIncrement: Button) {
            super.apply(textView, buttonDecrement, buttonIncrement)
            buttonIncrement.isEnabled = true
            buttonDecrement.isEnabled = true
        }
    }

    data class Min(
        private val text: String = "0"
    ) : Abstract(text) {
        override fun apply(textView: TextView, buttonDecrement: Button, buttonIncrement: Button) {
            super.apply(textView, buttonDecrement, buttonIncrement)
            buttonDecrement.isEnabled = false
            buttonIncrement.isEnabled = true
        }
    }

    data class Max(
        private val text: String
    ) : Abstract(text) {
        override fun apply(textView: TextView, buttonDecrement: Button, buttonIncrement: Button) {
            super.apply(textView, buttonDecrement, buttonIncrement)
            buttonIncrement.isEnabled = false
            buttonDecrement.isEnabled = true
        }
    }
}