package ru.easycode.zerotoheroandroidtdd.data

import java.lang.Exception
import java.net.UnknownHostException

interface Repository {

    suspend fun load(): LoadResult

    class Base(
        private val service: SimpleService,
        private val url: String
    ) : Repository {
        override suspend fun load(): LoadResult {
            return try {
                val response = service.fetch(url)
                LoadResult.Success(data = response)
            } catch (e: UnknownHostException) {
                LoadResult.Error(noConnection = true)
            } catch (e: Exception) {
                LoadResult.Error(noConnection = false)
            }
        }
    }
}
