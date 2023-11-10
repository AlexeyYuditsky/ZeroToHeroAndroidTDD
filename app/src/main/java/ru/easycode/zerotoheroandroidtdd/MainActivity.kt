package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private var state: UiState = UiState.Init
    private lateinit var textView: TextView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        textView = findViewById(R.id.countTextView)
        button = findViewById(R.id.incrementButton)

        button.setOnClickListener {
            val count = Count.Base(2, 4)
            state = count.increment(textView.text.toString())
            state.apply(textView, button)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        state = savedInstanceState.getSerializable(KEY) as UiState
        state.apply(textView, button)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(KEY, state)
        super.onSaveInstanceState(outState)
    }

    private companion object {
        const val KEY = "key"
    }

}