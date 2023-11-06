package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        textView = findViewById(R.id.titleTextView)
        val button = findViewById<Button>(R.id.hideButton)

        button.setOnClickListener { textView.isVisible = false }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        textView.isVisible = savedInstanceState.getBoolean("key")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("key", textView.isVisible)
        super.onSaveInstanceState(outState)
    }
}