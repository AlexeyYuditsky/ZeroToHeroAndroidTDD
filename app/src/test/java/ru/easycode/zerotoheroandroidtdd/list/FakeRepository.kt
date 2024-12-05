package ru.easycode.zerotoheroandroidtdd.list

import org.junit.Assert.assertEquals
import ru.easycode.zerotoheroandroidtdd.Order
import ru.easycode.zerotoheroandroidtdd.core.Repository
import ru.easycode.zerotoheroandroidtdd.core.model.Item

interface FakeRepository : Repository.Read {

    companion object {
        const val REPOSITORY_LIST = "repository#list"
    }

    fun expectList(list: List<Item>)

    fun checkListCalled(expected: List<Item>)

    class Base(
        private val order: Order
    ) : FakeRepository {

        private val list = mutableListOf<Item>()

        override fun list(): List<Item> {
            order.add(REPOSITORY_LIST)
            return list
        }

        override fun expectList(list: List<Item>) {
            this.list.addAll(list)
        }

        override fun checkListCalled(expected: List<Item>) {
            assertEquals(expected, list)
        }
    }

}