package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.actionButton.setOnClickListener {
            addTextView(binding.inputEditText.text.toString())
            binding.inputEditText.text?.clear()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val names = savedInstanceState.getStringArray("key") ?: return
        names.forEach { addTextView(it) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val array = binding.contentLayout.children.map { (it as TextView).text.toString() }.toArray()
        outState.putStringArray("key", array)
    }

    private fun addTextView(text: CharSequence) {
        val textView = TextView(this)
        textView.text = text
        binding.contentLayout.addView(textView)
    }

    private inline fun <reified T> Sequence<T>.toArray(): Array<T> = toList().toTypedArray()
}