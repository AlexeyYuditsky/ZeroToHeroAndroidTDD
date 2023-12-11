package ru.easycode.zerotoheroandroidtdd

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.easycode.zerotoheroandroidtdd.data.dataSource.SimpleResponse
import ru.easycode.zerotoheroandroidtdd.data.dataSource.SimpleService

/**
 * Hint for success: use encoded true for url
 */
class ServiceTest {

    @Test
    fun test() = runBlocking {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/AlexeyYuditsky/ZeroToHeroAndroidTDD/task/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: SimpleService = retrofit.create<SimpleService>()
        val actual = service.fetch(
            url = "018-clouddatasource/app/sampleresponse.json"
        )
        val expected = SimpleResponse(text = "Hello World From Web!")
        assertEquals(expected, actual)
    }
}