package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import org.junit.Assert.assertEquals
import ru.easycode.zerotoheroandroidtdd.core.ItemListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.core.model.ItemUi

interface FakeItemListLiveDataWrapper : ItemListLiveDataWrapper.All {

    companion object {
        const val LIVEDATA_DELETE = "itemListLiveDataWrapper#delete"
        const val LIVEDATA_UPDATE = "itemListLiveDataWrapper#update"
    }

    fun checkUpdateCallList(expected: List<ItemUi>)

    class Base(
        private val order: Order
    ) : FakeItemListLiveDataWrapper {

        private val actual = mutableListOf<ItemUi>()

        override fun checkUpdateCallList(expected: List<ItemUi>) {
            assertEquals(expected, actual)
        }

        override fun update(value: List<ItemUi>) {
            order.add(LIVEDATA_UPDATE)
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
            order.add(LIVEDATA_DELETE)
            actual.remove(item)
        }
    }
}