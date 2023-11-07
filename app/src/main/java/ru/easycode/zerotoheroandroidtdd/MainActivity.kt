package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.TextView
import kotlinx.parcelize.Parcelize
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var button: Button
    private lateinit var textView: TextView
    private var state: State = State.Initial

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        textView = findViewById(R.id.countTextView)
        button = findViewById(R.id.incrementButton)
        button.setOnClickListener {
            state = State.Counter
            state.increase(textView)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState.getParcelable(KEY, State::class.java) as State
        } else {
            savedInstanceState.getParcelable<State>(KEY) as State
        }
        state.apply(textView)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(KEY, state)
        super.onSaveInstanceState(outState)
    }

    private companion object {
        const val KEY = "key"
    }
}

@Parcelize
open class State : Parcelable {
    open fun apply(textView: TextView) = Unit
    open fun increase(textView: TextView) = Unit

    object Initial : State()
    object Counter : State() {
        private lateinit var text: CharSequence
        override fun apply(textView: TextView) {
            textView.text = text
        }

        override fun increase(textView: TextView) {
            val count = Count.Base(2)
            val res = count.increment(textView.text.toString())
            text = res
            textView.text = res
        }
    }
}

interface Count {
    fun increment(number: String): String
    class Base(step: Int) : Count {
        private var step by Delegates.notNull<Int>()

        init {
            if (step <= 0) throw IllegalStateException("step should be positive, but was $step")
            this.step = step
        }

        override fun increment(number: String): String = (number.toInt() + step).toString()
    }
}