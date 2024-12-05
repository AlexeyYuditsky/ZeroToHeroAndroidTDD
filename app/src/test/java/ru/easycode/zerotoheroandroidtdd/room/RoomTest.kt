package ru.easycode.zerotoheroandroidtdd.room

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import ru.easycode.zerotoheroandroidtdd.core.ItemsDataBase
import ru.easycode.zerotoheroandroidtdd.core.model.ItemCache

/**
 * This is test for Room, no ui expected
 */
@RunWith(AndroidJUnit4::class)
class RoomTest {

    private val db = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        ItemsDataBase::class.java
    ).allowMainThreadQueries().build()

    private val dao = db.itemsDao()

    @Test
    fun test_add() {
        assertEquals(emptyList<ItemCache>(), dao.list())

        dao.add(item = ItemCache(id = 1L, text = "first"))

        val actual1 = dao.list()
        val expected1 = listOf(ItemCache(id = 1L, text = "first"))
        assertEquals(expected1, actual1)

        dao.add(item = ItemCache(id = 2L, text = "second"))

        val expected2 = listOf(
            ItemCache(id = 1L, text = "first"),
            ItemCache(id = 2L, text = "second")
        )
        val actual2 = dao.list()
        assertEquals(expected2, actual2)
    }

    @Test
    fun test_item() {
        assertEquals(emptyList<ItemCache>(), dao.list())

        dao.add(item = ItemCache(id = 1L, text = "first"))

        val actual1 = dao.item(id = 1L)
        val expected1 = ItemCache(id = 1L, text = "first")
        assertEquals(expected1, actual1)

        dao.add(item = ItemCache(id = 2L, text = "second"))

        val actual2 = dao.item(id = 2L)
        val expected2 = ItemCache(id = 2L, text = "second")
        assertEquals(expected2, actual2)

        val actual3 = dao.list()
        val expected3 = listOf(
            ItemCache(id = 1L, text = "first"),
            ItemCache(id = 2L, text = "second")
        )
        assertEquals(expected3, actual3)
    }

    @Test
    fun test_delete() {
        dao.add(item = ItemCache(id = 1L, text = "first"))
        dao.add(item = ItemCache(id = 2L, text = "second"))

        val expected1 = listOf(
            ItemCache(id = 1L, text = "first"),
            ItemCache(id = 2L, text = "second")
        )
        val actual1 = dao.list()
        assertEquals(expected1, actual1)

        dao.delete(id = 1L)

        val expected2 = listOf(ItemCache(id = 2L, text = "second"))
        val actual2 = dao.list()
        assertEquals(expected2, actual2)
    }

    @Test
    fun test_list() {
        dao.add(item = ItemCache(id = 1L, "text1"))
        dao.add(item = ItemCache(id = 1L, "text2"))

        val expected = listOf(ItemCache(id = 1L, "text2"))
        val actual = dao.list()
        assertEquals(expected, actual)
    }

}