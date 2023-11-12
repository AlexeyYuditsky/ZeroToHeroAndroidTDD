package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.TextView
import java.io.Serializable

interface UiState : Serializable {

    fun apply(textView: TextView, decrementButton: Button, incrementButton: Button) = Unit

    abstract class Abstract(
        private val text: String
    ) : UiState {
        override fun apply(textView: TextView, decrementButton: Button, incrementButton: Button) {
            textView.text = text
        }
    }

    data class Base(
        private val text: String
    ) : Abstract(text) {
        override fun apply(textView: TextView, decrementButton: Button, incrementButton: Button) {
            super.apply(textView, decrementButton, incrementButton)
            incrementButton.isEnabled = true
            decrementButton.isEnabled = true
        }
    }

    data class Min(
        private val text: String
    ) : Abstract(text) {
        override fun apply(textView: TextView, decrementButton: Button, incrementButton: Button) {
            super.apply(textView, decrementButton, incrementButton)
            decrementButton.isEnabled = false
            incrementButton.isEnabled = true
        }
    }

    data class Max(
        private val text: String
    ) : Abstract(text) {
        override fun apply(textView: TextView, decrementButton: Button, incrementButton: Button) {
            super.apply(textView, decrementButton, incrementButton)
            incrementButton.isEnabled = false
            decrementButton.isEnabled = true
        }
    }
}