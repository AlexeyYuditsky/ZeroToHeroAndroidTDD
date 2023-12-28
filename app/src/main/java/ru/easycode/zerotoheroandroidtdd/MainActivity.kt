package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import kotlinx.parcelize.Parcelize
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private var uiState: UiState = UiState.Empty

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.actionButton.setOnClickListener {
            uiState = UiState.Data(binding.inputEditText.text.toString())
            uiState.apply(binding.inputEditText, binding.titleTextView)
        }

        uiState.apply(binding.inputEditText, binding.titleTextView)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val uiState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState.getParcelable("key", UiState::class.java) as UiState
        } else {
            savedInstanceState.getParcelable<UiState>("key") as UiState
        }
        this.uiState = uiState
        uiState.apply(binding.inputEditText, binding.titleTextView)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable("key", uiState)
        super.onSaveInstanceState(outState)
    }
}

sealed interface UiState : Parcelable {

    fun apply(textInputEditText: TextInputEditText, titleTextVew: TextView)

    @Parcelize
    data object Empty : UiState {

        override fun apply(
            textInputEditText: TextInputEditText,
            titleTextVew: TextView
        ) {
            titleTextVew.text = "Hello World!"
        }
    }

    @Parcelize
    data class Data(
        private val text: String
    ) : UiState {

        override fun apply(
            textInputEditText: TextInputEditText,
            titleTextVew: TextView
        ) {
            textInputEditText.setText("")
            titleTextVew.text = text
        }
    }
}