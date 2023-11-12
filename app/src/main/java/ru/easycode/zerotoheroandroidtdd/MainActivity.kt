package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import java.util.Timer
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.actionButton.setOnClickListener { onActionButtonPressed() }
    }

    private fun onActionButtonPressed() = with(binding) {
        UiState.Progress.apply(titleTextView, actionButton, progressBar)
        Timer().schedule(timerTask {
            runOnUiThread {
                UiState.Finish.apply(titleTextView, actionButton, progressBar)
            }
        }, 3000)
    }
}

interface UiState {

    fun apply(textView: TextView, button: Button, progressBar: ProgressBar) = Unit

    object Progress : UiState {
        override fun apply(textView: TextView, button: Button, progressBar: ProgressBar) {
            textView.isInvisible = true
            button.isEnabled = false
            progressBar.isVisible = true
        }
    }

    object Finish : UiState {
        override fun apply(textView: TextView, button: Button, progressBar: ProgressBar) {
            textView.isInvisible = false
            button.isEnabled = true
            progressBar.isInvisible = true
        }
    }
}