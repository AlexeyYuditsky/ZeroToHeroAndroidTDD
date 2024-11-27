package ru.easycode.zerotoheroandroidtdd

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import ru.easycode.zerotoheroandroidtdd.core.ItemCache
import ru.easycode.zerotoheroandroidtdd.core.ItemsDao
import ru.easycode.zerotoheroandroidtdd.core.ItemsDataBase

/**
 * This is test for Room, no ui expected
 */
@RunWith(AndroidJUnit4::class)
class RoomTest {

    private val db: ItemsDataBase = Room.inMemoryDatabaseBuilder(
        context = ApplicationProvider.getApplicationContext(),
        klass = ItemsDataBase::class.java
    ).allowMainThreadQueries().build()

    private val dao: ItemsDao = db.itemsDao()

    @Test
    fun test() {
        val expected1 = emptyList<ItemCache>()
        val actual1 = dao.list()
        assertEquals(expected1, actual1)

        val cache = ItemCache(id = 1L, text = "first")
        dao.add(item = cache)

        val expected2 = listOf(ItemCache(id = 1L, text = "first"))
        val actual2 = dao.list()
        assertEquals(expected2, actual2)

        val next = ItemCache(id = 2L, text = "second")
        dao.add(item = next)

        val expected3 = listOf(
            ItemCache(id = 1L, text = "first"),
            ItemCache(id = 2L, text = "second")
        )
        val actual3 = dao.list()
        assertEquals(expected3, actual3)
    }

}