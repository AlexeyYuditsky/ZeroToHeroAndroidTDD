package ru.easycode.zerotoheroandroidtdd.repository

import org.junit.Assert.assertEquals
import ru.easycode.zerotoheroandroidtdd.core.ItemsDao
import ru.easycode.zerotoheroandroidtdd.core.model.ItemCache

interface FakeDataSource : ItemsDao {

    fun checkList(expected: List<ItemCache>)

    fun expectList(list: List<ItemCache>)

    class Base : FakeDataSource {

        private val list = mutableListOf<ItemCache>()

        override fun checkList(expected: List<ItemCache>) {
            assertEquals(expected, list)
        }

        override fun expectList(list: List<ItemCache>) {
            this.list.addAll(list)
        }

        override fun list(): List<ItemCache> {
            return list
        }

        override fun add(item: ItemCache) {
            list.add(item)
        }

        override fun item(id: Long): ItemCache {
            return list.find { it.id == id }!!
        }

        override fun delete(id: Long) {
            list.remove(item(id))
        }

    }

}