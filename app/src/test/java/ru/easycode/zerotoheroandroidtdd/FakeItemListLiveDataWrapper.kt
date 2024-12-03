package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import org.junit.Assert.assertEquals
import ru.easycode.zerotoheroandroidtdd.core.ItemListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.core.model.ItemUi

interface FakeItemListLiveDataWrapper : ItemListLiveDataWrapper.All {

    companion object {
        const val LIVE_DATA_DELETE = "ItemListLiveDataWrapper#delete"
    }

    fun checkUpdateCallList(expected: List<ItemUi>)

    class Base(private val order: Order = Order()) : FakeItemListLiveDataWrapper {

        private val actual = mutableListOf<ItemUi>()

        override fun checkUpdateCallList(expected: List<ItemUi>) {
            assertEquals(expected, actual)
        }

        override fun update(value: List<ItemUi>) {
            actual.clear()
            actual.addAll(value)
        }

        override fun liveData(): LiveData<List<ItemUi>> {
            throw IllegalStateException("not used")
        }

        override fun add(item: ItemUi) {
            actual.add(item)
        }

        override fun delete(item: ItemUi) {
            order.add(LIVE_DATA_DELETE)
            actual.remove(item)
        }
    }
}