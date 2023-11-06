package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.parcelize.Parcelize

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Parcelize
    open class State : Parcelable {
        open fun apply(linearLayout: LinearLayout, textView: TextView) = Unit

        object Initial : State()

        object Removed : State() {
            override fun apply(linearLayout: LinearLayout, textView: TextView) {
                linearLayout.removeView(textView)
            }
        }
    }

    private var state: State = State.Initial
    private lateinit var linearLayout: LinearLayout
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linearLayout = findViewById(R.id.rootLayout)
        textView = findViewById(R.id.titleTextView)
        val button = findViewById<Button>(R.id.removeButton)
        button.setOnClickListener {
            state = State.Removed
            state.apply(linearLayout, textView)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            savedInstanceState.getParcelable(KEY, State::class.java) as State
        else
            savedInstanceState.getParcelable<State>(KEY) as State

        state.apply(linearLayout, textView)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(KEY, state)
        super.onSaveInstanceState(outState)
    }

    private companion object {
        const val KEY = "key"
    }
}