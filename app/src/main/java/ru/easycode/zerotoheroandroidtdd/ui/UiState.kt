package ru.easycode.zerotoheroandroidtdd.ui

import android.os.Parcelable
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import kotlinx.parcelize.Parcelize

interface UiState : Parcelable {

    fun apply(
        progressBar: ProgressBar,
        textView: TextView,
        button: Button
    )

    @Parcelize
    data object ShowProgress : UiState {
        override fun apply(
            progressBar: ProgressBar,
            textView: TextView,
            button: Button
        ) {
            progressBar.isVisible = true
            textView.isVisible = false
            button.isEnabled = false
        }
    }

    @Parcelize
    data class ShowData(
        private val text: String
    ) : UiState {
        override fun apply(
            progressBar: ProgressBar,
            textView: TextView,
            button: Button
        ) {
            progressBar.isVisible = false
            textView.isVisible = true
            textView.text = text
            button.isEnabled = true
        }
    }
}