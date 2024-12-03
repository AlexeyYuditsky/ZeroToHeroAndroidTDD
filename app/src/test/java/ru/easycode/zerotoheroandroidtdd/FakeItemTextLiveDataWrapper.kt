package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import org.junit.Assert.assertEquals
import ru.easycode.zerotoheroandroidtdd.delete.ItemTextLiveDataWrapper

interface FakeItemTextLiveDataWrapper : ItemTextLiveDataWrapper.Mutable {

    fun checkUpdateCalled(expected: String)

    class Base(
        private val order: Order
    ) : FakeItemTextLiveDataWrapper {

        private var actual = ""

        override fun checkUpdateCalled(expected: String) {
            assertEquals(expected, actual)
        }

        override fun update(value: String) {
            actual = value
        }

        override fun liveData(): LiveData<String> = throw IllegalStateException("not used")
    }
}