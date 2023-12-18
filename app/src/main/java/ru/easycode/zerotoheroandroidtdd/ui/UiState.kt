package ru.easycode.zerotoheroandroidtdd.ui

import android.os.Parcelable
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import kotlinx.parcelize.Parcelize

interface UiState : Parcelable {

    fun apply(
        textView: TextView,
        progressBar: ProgressBar,
        button: Button
    )

    @Parcelize
    data class ShowData(
        private val text: String
    ) : UiState {
        override fun apply(
            textView: TextView,
            progressBar: ProgressBar,
            button: Button
        ) {
            textView.text = text
            textView.isVisible = true
            progressBar.isVisible = false
            button.isEnabled = true
        }
    }

    @Parcelize
    object ShowProgress : UiState {
        override fun apply(
            textView: TextView,
            progressBar: ProgressBar,
            button: Button
        ) {
            textView.isVisible = false
            progressBar.isVisible = true
            button.isEnabled = false
        }
    }
}
