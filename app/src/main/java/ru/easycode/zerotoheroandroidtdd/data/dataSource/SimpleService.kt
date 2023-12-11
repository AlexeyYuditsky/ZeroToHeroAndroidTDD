package ru.easycode.zerotoheroandroidtdd.data.dataSource

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface SimpleService {

    @GET
    suspend fun fetch(
        @Url url: String
    ): SimpleResponse

    companion object {
        private const val URL =
            "https://raw.githubusercontent.com/AlexeyYuditsky/ZeroToHeroAndroidTDD/task/"
        const val URL_PATH = "018-clouddatasource/app/sampleresponse.json"
        private lateinit var service: SimpleService

        fun getService(): SimpleService =
            if (::service.isInitialized) {
                service
            } else {
                Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create<SimpleService>()
                    .also { service = it }
            }
    }
}