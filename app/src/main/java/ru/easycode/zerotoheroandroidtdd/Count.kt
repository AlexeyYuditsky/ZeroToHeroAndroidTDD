package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.TextView
import java.io.Serializable

interface Count {

    fun increment(number: String): UiState

    data class Base(
        private val step: Int,
        private val max: Int
    ) : Count {

        init {
            when {
                step < 1 -> throw IllegalStateException("step should be positive, but was $step")
                max < 1 -> throw IllegalStateException("max should be positive, but was $max")
                step > max -> throw IllegalStateException("max should be more than step")
            }
        }

        override fun increment(number: String): UiState {
            val result = (step + number.toInt())
            return if (result == number.toInt() * 2) {
                UiState.Max(result.toString())
            } else {
                UiState.Base(result.toString())
            }
        }
    }

}

interface UiState : Serializable {

    fun apply(textView: TextView, button: Button) = Unit

    object Init : UiState

    data class Base(
        private val text: String
    ) : UiState {
        override fun apply(textView: TextView, button: Button) {
            textView.text = text
        }
    }

    data class Max(
        private val text: String
    ) : UiState {
        override fun apply(textView: TextView, button: Button) {
            textView.text = text
            button.isEnabled = false
        }
    }

}
