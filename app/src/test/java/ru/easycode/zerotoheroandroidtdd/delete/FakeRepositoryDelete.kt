package ru.easycode.zerotoheroandroidtdd.delete

import org.junit.Assert.assertEquals
import ru.easycode.zerotoheroandroidtdd.Order
import ru.easycode.zerotoheroandroidtdd.core.Repository
import ru.easycode.zerotoheroandroidtdd.core.model.Item
import kotlin.properties.Delegates

interface FakeRepositoryDelete : Repository.Delete {

    companion object {
        const val REPOSITORY_DELETE = "repository#delete"
        const val REPOSITORY_ITEM = "repository#item"
    }

    fun checkDeleteCalled(id: Long)

    fun checkItemCalled(item: Item)

    class Base(
        private val order: Order
    ) : FakeRepositoryDelete {

        private var actualId by Delegates.notNull<Long>()
        private lateinit var item: Item

        override fun item(id: Long): Item {
            order.add(REPOSITORY_ITEM)
            val item = Item(id, id.toString())
            this.item = item
            return item
        }

        override fun delete(id: Long) {
            order.add(REPOSITORY_DELETE)
            actualId = id
        }

        override fun checkItemCalled(item: Item) {
            assertEquals(item, this.item)
        }

        override fun checkDeleteCalled(id: Long) {
            assertEquals(id, actualId)
        }

    }

}