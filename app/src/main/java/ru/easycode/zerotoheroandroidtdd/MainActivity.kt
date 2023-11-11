package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var textView: TextView
    private lateinit var buttonIncrement: Button
    private lateinit var buttonDecrement: Button

    private var state: UiState = UiState.Min()
    private val count: Count = Count.Base(2, 4, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        textView = findViewById(R.id.countTextView)
        buttonIncrement = findViewById(R.id.incrementButton)
        buttonDecrement = findViewById(R.id.decrementButton)
        buttonIncrement.setOnClickListener { onButtonIncrementListener() }
        buttonDecrement.setOnClickListener { onButtonDecrementListener() }
        state.apply(textView, buttonDecrement, buttonIncrement)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState.getSerializable(KEY, UiState::class.java) as UiState
        } else {
            @Suppress("DEPRECATION")
            savedInstanceState.getSerializable(KEY) as UiState
        }
        state.apply(textView, buttonDecrement, buttonIncrement)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(KEY, state)
        super.onSaveInstanceState(outState)
    }

    private fun onButtonIncrementListener() {
        state = count.increment(textView.text.toString())
        state.apply(textView, buttonDecrement, buttonIncrement)
    }

    private fun onButtonDecrementListener() {
        state = count.decrement(textView.text.toString())
        state.apply(textView, buttonDecrement, buttonIncrement)
    }

    private companion object {
        const val KEY = "key"
    }
}