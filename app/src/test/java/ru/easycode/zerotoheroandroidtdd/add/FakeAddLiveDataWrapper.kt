package ru.easycode.zerotoheroandroidtdd.add

import org.junit.Assert.assertEquals
import ru.easycode.zerotoheroandroidtdd.Order
import ru.easycode.zerotoheroandroidtdd.core.ItemListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.core.model.ItemUi

interface FakeAddLiveDataWrapper : ItemListLiveDataWrapper.Add {

    companion object {
        const val LIVEDATA_ADD = "liveData#add"
    }

    fun checkAddCalled(expected: ItemUi)

    class Base(
        private val order: Order
    ) : FakeAddLiveDataWrapper {

        private lateinit var actual: ItemUi

        override fun add(item: ItemUi) {
            order.add(LIVEDATA_ADD)
            actual = item
        }

        override fun checkAddCalled(expected: ItemUi) {
            assertEquals(expected, actual)
        }

    }

}