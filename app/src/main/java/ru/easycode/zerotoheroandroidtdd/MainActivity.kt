package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var textView: TextView
    private lateinit var incrementButton: Button
    private lateinit var decrementButton: Button

    private val count: Count = Count.Base(2, 4, 0)
    private lateinit var state: UiState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        textView = findViewById(R.id.countTextView)
        incrementButton = findViewById(R.id.incrementButton)
        decrementButton = findViewById(R.id.decrementButton)
        incrementButton.setOnClickListener { onButtonIncrementListener() }
        decrementButton.setOnClickListener { onButtonDecrementListener() }

        if (savedInstanceState == null) {
            state = count.initial(textView.text.toString())
            state.apply(textView, decrementButton, incrementButton)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        state = count.initial(textView.text.toString())
        state.apply(textView, decrementButton, incrementButton)
    }

    private fun onButtonIncrementListener() {
        state = count.increment(textView.text.toString())
        state.apply(textView, decrementButton, incrementButton)
    }

    private fun onButtonDecrementListener() {
        state = count.decrement(textView.text.toString())
        state.apply(textView, decrementButton, incrementButton)
    }
}