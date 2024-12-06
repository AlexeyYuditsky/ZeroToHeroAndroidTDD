package ru.easycode.zerotoheroandroidtdd.core

import ru.easycode.zerotoheroandroidtdd.core.model.Item
import ru.easycode.zerotoheroandroidtdd.core.model.ItemCache

interface Repository {

    interface ReadItem {
        fun item(id: Long): Item
    }

    interface ChangeItem {
        fun update(id: Long, newText: String)
        fun delete(id: Long)
    }

    interface Read {
        fun list(): List<Item>
    }

    interface Add {
        fun add(value: String): Long
    }

    interface Mutable : Read, Add

    interface Change : ReadItem, ChangeItem

    interface All : Mutable, Change

    class Base(
        private val dataSource: ItemsDao,
        private val now: Now
    ) : All {

        override fun list(): List<Item> {
            return dataSource.list().map { Item(it.id, it.text) }
        }

        override fun add(value: String): Long {
            val id = now.nowMillis()
            dataSource.add(ItemCache(id, value))
            return id
        }

        override fun item(id: Long): Item {
            val itemCache = dataSource.item(id)
            return Item(itemCache.id, itemCache.text)
        }

        override fun update(id: Long, newText: String) {
            val itemCache = ItemCache(id = id, text = newText)
            dataSource.add(itemCache)
        }

        override fun delete(id: Long) {
            dataSource.delete(id)
        }

    }

}