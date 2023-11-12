package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isInvisible

interface UiState {

    fun apply(progress: ProgressBar, textView: TextView, button: Button)

    object ShowData : UiState {
        override fun apply(progress: ProgressBar, textView: TextView, button: Button) {
            progress.isInvisible = true
            textView.isInvisible = false
            button.isEnabled = true
        }
    }
    object ShowProgress : UiState {
        override fun apply(progress: ProgressBar, textView: TextView, button: Button) {
            progress.isInvisible = false
            textView.isInvisible = true
            button.isEnabled = false
        }
    }
}