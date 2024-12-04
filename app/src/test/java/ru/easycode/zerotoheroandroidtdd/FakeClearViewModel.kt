package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel
import org.junit.Assert.assertEquals
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModel

interface FakeClearViewModel : ClearViewModel {

    companion object {
        const val CLEAR_VIEWMODEL = "clearCall"
    }

    fun checkClearCalled(expected: Class<out ViewModel>)

    class Base(
        private val order: Order
    ) : FakeClearViewModel {

        private lateinit var actual: Class<out ViewModel>

        override fun clearViewModel(viewModelClass: Class<out ViewModel>) {
            order.add(CLEAR_VIEWMODEL)
            actual = viewModelClass
        }

        override fun checkClearCalled(expected: Class<out ViewModel>) {
            assertEquals(expected, actual)
        }

    }

}