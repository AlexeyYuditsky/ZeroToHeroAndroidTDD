package ru.easycode.zerotoheroandroidtdd.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.actionButton.setOnClickListener { onActionButtonPressed() }
        viewModel.liveData().observe(this, ::updateUiState)
    }

    private fun onActionButtonPressed() = viewModel.load()

    private fun updateUiState(uiState: UiState) = with(binding) {
        uiState.apply(progressBar, titleTextView, actionButton)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val bundleWrapper = BundleWrapper.Base(savedInstanceState)
        viewModel.restore(bundleWrapper)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val bundleWrapper = BundleWrapper.Base(outState)
        viewModel.save(bundleWrapper)
        super.onSaveInstanceState(outState)
    }
}