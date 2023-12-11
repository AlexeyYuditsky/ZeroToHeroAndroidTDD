package ru.easycode.zerotoheroandroidtdd.data.repository

import ru.easycode.zerotoheroandroidtdd.data.dataSource.SimpleResponse
import ru.easycode.zerotoheroandroidtdd.data.dataSource.SimpleService

interface Repository {

    suspend fun load(): SimpleResponse

    class Base(
        private val service: SimpleService,
        private val url: String
    ) : Repository {

        override suspend fun load(): SimpleResponse = service.fetch(url)
    }
}