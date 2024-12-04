package ru.easycode.zerotoheroandroidtdd.add

import org.junit.Assert.assertEquals
import ru.easycode.zerotoheroandroidtdd.Order
import ru.easycode.zerotoheroandroidtdd.core.Repository

interface FakeAddRepository : Repository.Add {

    companion object {
        const val REPOSITORY_ADD = "repository#add"
    }

    fun checkAddCalled(expected: String)

    class Base(
        private val order: Order
    ) : FakeAddRepository {

        private lateinit var actual: String
        private var count = 10L

        override fun add(value: String): Long {
            order.add(REPOSITORY_ADD)
            actual = value
            return count++
        }

        override fun checkAddCalled(expected: String) {
            assertEquals(expected, actual)
        }

    }

}