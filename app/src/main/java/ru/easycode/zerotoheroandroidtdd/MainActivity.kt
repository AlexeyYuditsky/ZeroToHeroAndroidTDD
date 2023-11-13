package ru.easycode.zerotoheroandroidtdd

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by lazy { getMainViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.actionButton.setOnClickListener {
            viewModel.load()
        }

        viewModel.liveData().observe(this) { uiState ->
            uiState.apply(binding.titleTextView, binding.actionButton, binding.progressBar)
        }
    }
}

fun Activity.getMainViewModel() = (application as App).mainViewModel