package ru.easycode.zerotoheroandroidtdd.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.App
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by lazy { (application as App).mainViewModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.liveData().observe(this, ::updateUiState)
        binding.actionButton.setOnClickListener { onButtonPressed() }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val bundleWrapper = BundleWrapper.Base(savedInstanceState)
        viewModel.restore(bundleWrapper)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val bundleWrapper = BundleWrapper.Base(outState)
        viewModel.save(bundleWrapper)
    }

    private fun updateUiState(uiState: UiState) = with(binding) {
        uiState.apply(titleTextView, progressBar, actionButton)
    }

    private fun onButtonPressed() = viewModel.load()
}