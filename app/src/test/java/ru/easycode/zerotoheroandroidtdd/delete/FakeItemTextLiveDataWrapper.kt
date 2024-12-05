package ru.easycode.zerotoheroandroidtdd.delete

import androidx.lifecycle.LiveData
import org.junit.Assert.assertEquals
import ru.easycode.zerotoheroandroidtdd.Order

interface FakeItemTextLiveDataWrapper : ItemTextLiveDataWrapper.Mutable {

    fun checkUpdateCalled(expected: String)

    companion object {
        const val LIVEDATA_ADD = "itemTextLiveDataWrapper#add"
        const val LIVEDATA_VALUE = "itemTextLiveDataWrapper#value"
    }

    class Base(
        private val order: Order
    ) : FakeItemTextLiveDataWrapper {

        private var actual = ""

        override fun update(value: String) {
            order.add(LIVEDATA_ADD)
            actual = value
        }

        override fun liveData(): LiveData<String> = throw IllegalStateException("not used")

        override fun liveDataValue(): String {
            order.add(LIVEDATA_VALUE)
            return actual
        }

        override fun checkUpdateCalled(expected: String) {
            assertEquals(expected, actual)
        }

    }

}