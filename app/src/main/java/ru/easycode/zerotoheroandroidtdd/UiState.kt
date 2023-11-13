package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible

interface UiState {

    fun apply(textView: TextView, button: Button, progressBar: ProgressBar)

    object ShowProgress : UiState {
        override fun apply(textView: TextView, button: Button, progressBar: ProgressBar) {
            textView.isVisible = false
            button.isEnabled = false
            progressBar.isVisible = true
        }
    }

    object ShowData : UiState {
        override fun apply(textView: TextView, button: Button, progressBar: ProgressBar) {
            textView.isVisible = true
            button.isEnabled = true
            progressBar.isVisible = false
        }
    }
}