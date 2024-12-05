package ru.easycode.zerotoheroandroidtdd.repository

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.easycode.zerotoheroandroidtdd.core.Repository
import ru.easycode.zerotoheroandroidtdd.core.model.Item
import ru.easycode.zerotoheroandroidtdd.core.model.ItemCache

class RepositoryTest {

    private val now = FakeNow.Base(7777L)
    private val dataSource = FakeDataSource.Base()
    private val repository = Repository.Base(dataSource = dataSource, now = now)

    @Test
    fun test_add() {
        dataSource.expectList(
            listOf(
                ItemCache(id = 0L, text = "first"),
                ItemCache(id = 1L, text = "second")
            )
        )

        repository.add(value = "third")

        dataSource.checkList(
            listOf(
                ItemCache(id = 0L, text = "first"),
                ItemCache(id = 1L, text = "second"),
                ItemCache(id = 7778L, text = "third")
            )
        )
    }

    @Test
    fun test_item() {
        repository.add(value = "first")
        dataSource.checkList(listOf(ItemCache(id = 7778L, text = "first")))

        repository.item(id = 7778L)

        val actual = repository.item(id = 7778L)
        val expected = Item(id = 7778L, text = "first")
        assertEquals(expected, actual)
    }

    @Test
    fun test_delete() {
        repository.add("first")
        dataSource.checkList(listOf(ItemCache(id = 7778L, text = "first")))

        repository.add(value = "second")
        dataSource.checkList(
            listOf(
                ItemCache(id = 7778L, text = "first"),
                ItemCache(7779L, text = "second")
            )
        )

        repository.delete(id = 7778L)
        dataSource.checkList(listOf(ItemCache(7779L, text = "second")))
    }
}