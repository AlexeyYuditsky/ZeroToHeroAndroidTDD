package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val textWatcher = binding.inputEditText.addTextChangedListener {
            binding.actionButton.isEnabled = it.toString().length > 2
        }

        binding.actionButton.setOnClickListener {
            binding.titleTextView.text = binding.inputEditText.text
            binding.inputEditText.removeTextChangedListener(textWatcher)
            binding.inputEditText.text?.clear()
            binding.inputEditText.addTextChangedListener(textWatcher)
        }
    }
}