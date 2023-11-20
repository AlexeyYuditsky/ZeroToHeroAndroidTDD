package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.actionButton.setOnClickListener {
            viewModel.load()
        }

        viewModel.liveData().observe(this) { uiState ->
            uiState.apply(binding.progressBar, binding.titleTextView, binding.actionButton)
        }
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