package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var button: Button
    private lateinit var textView: TextView
    private val count = Count.Base(2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        textView = findViewById(R.id.countTextView)
        button = findViewById(R.id.incrementButton)
        button.setOnClickListener {
            textView.text = count.increment(textView.text.toString())
        }
    }
}

