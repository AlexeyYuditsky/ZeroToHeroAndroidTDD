package ru.easycode.zerotoheroandroidtdd

interface Count {

    fun initial(number: String): UiState
    fun decrement(number: String): UiState
    fun increment(number: String): UiState

    data class Base(
        private val step: Int,
        private val max: Int,
        private val min: Int
    ) : Count {

        init {
            when {
                step < 1 -> throw IllegalStateException("step should be positive, but was $step")
                max < 1 -> throw IllegalStateException("max should be positive, but was $max")
                max < step -> throw IllegalStateException("max should be more than step")
                max < min -> throw IllegalStateException("max should be more than min")
            }
        }

        override fun initial(number: String): UiState {
            val digits = number.toInt()
            return when {
                digits <= min -> UiState.Min(number)
                digits >= max -> UiState.Max(number)
                else -> UiState.Base(number)
            }
        }

        override fun decrement(number: String): UiState {
            val digit = number.toInt()
            val res = (digit - step).toString()
            return initial(res)
        }

        override fun increment(number: String): UiState {
            val digit = number.toInt()
            val res = (digit + step).toString()
            return initial(res)
        }
    }
}