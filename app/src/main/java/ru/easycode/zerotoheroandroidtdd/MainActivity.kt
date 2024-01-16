package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter = TextAdapter()
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = (application as App).viewModel

        viewModel.liveData().observe(this) {
            adapter.submitList(it)
        }

        binding.recyclerView.adapter = adapter
        binding.actionButton.setOnClickListener { onActionButtonPressed() }
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

    private fun onActionButtonPressed() = with(binding) {
        val text = inputEditText.text.toString()
        viewModel.add(text)
        inputEditText.text?.clear()
    }
}