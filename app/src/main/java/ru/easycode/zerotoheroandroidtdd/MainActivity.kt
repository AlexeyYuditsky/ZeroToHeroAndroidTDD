package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel = MainViewModel(LiveDataWrapper.Base(), Repository.Base())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            actionButton.setOnClickListener { onButtonPressed() }
            viewModel.liveData().observe(this@MainActivity) { uiState ->
                uiState.apply(progressBar, titleTextView, actionButton)
            }
        }
    }

    private fun onButtonPressed() = viewModel.load()
}