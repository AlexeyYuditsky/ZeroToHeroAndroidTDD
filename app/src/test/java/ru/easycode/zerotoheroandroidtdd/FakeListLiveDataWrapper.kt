package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import org.junit.Assert.assertEquals
import ru.easycode.zerotoheroandroidtdd.core.ItemListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.core.model.ItemUi

interface FakeListLiveDataWrapper : ItemListLiveDataWrapper.All {

    companion object {
        const val LIVE_DATA_DELETE = "ListLiveDataWrapper#delete"
        const val LIVE_DATA_UPDATE = "ListLiveDataWrapper#update"
    }

    fun checkUpdateCallList(expected: List<ItemUi>)

    class Base(
        private val order: Order
    ) : FakeListLiveDataWrapper {

        private val actual = mutableListOf<ItemUi>()

        override fun checkUpdateCallList(expected: List<ItemUi>) {
            assertEquals(expected, actual)
        }

        override fun update(value: ItemUi) {
            order.add(LIVE_DATA_UPDATE)
            actual.find { it.areItemsSame(value) }?.let {
                actual[actual.indexOf(it)] = value
            }
        }

        override fun update(value: List<ItemUi>) {
            actual.clear()
            actual.addAll(value)
        }

        override fun liveData(): LiveData<List<ItemUi>> {
            throw IllegalStateException("not used")
        }

        override fun add(value: ItemUi) {
            actual.add(value)
        }

        override fun delete(value: ItemUi) {
            order.add(LIVE_DATA_DELETE)
            actual.remove(value)
        }
    }
}