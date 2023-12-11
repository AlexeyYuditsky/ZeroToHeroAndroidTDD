package ru.easycode.zerotoheroandroidtdd

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import ru.easycode.zerotoheroandroidtdd.data.dataSource.SimpleResponse
import ru.easycode.zerotoheroandroidtdd.data.dataSource.SimpleService
import ru.easycode.zerotoheroandroidtdd.data.repository.Repository

class RepositoryTest {

    @Test
    fun test() = runBlocking {
        val service = FakeService.Base()
        val repository = Repository.Base(service = service, url = "a")
        val actual = repository.load()
        val expected = SimpleResponse(text = "A")
        assertEquals(expected, actual)
    }
}

private interface FakeService : SimpleService {

    class Base : FakeService {

        private val map = mapOf(
            "a" to SimpleResponse(text = "A")
        )

        override suspend fun fetch(url: String): SimpleResponse {
            return map[url]!!
        }
    }
}