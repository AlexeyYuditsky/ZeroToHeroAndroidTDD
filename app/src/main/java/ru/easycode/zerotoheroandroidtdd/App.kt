package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.easycode.zerotoheroandroidtdd.ui.LiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.ui.MainViewModel
import ru.easycode.zerotoheroandroidtdd.data.Repository
import ru.easycode.zerotoheroandroidtdd.data.SimpleService

class App : Application() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()
        mainViewModel = MainViewModel(
            LiveDataWrapper.Base(),
            Repository.Base(createRetrofit(), URL)
        )
    }

    private fun createRetrofit(): SimpleService {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
        return Retrofit.Builder()
            .baseUrl("https://github.com/AlexeyYuditsky/ZeroToHeroAndroidTDD/blob/task/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    private companion object {
        const val URL =
            "https://raw.githubusercontent.com/AlexeyYuditsky/ZeroToHeroAndroidTDD/task/018-clouddatasource/app/sampleresponse.json"
    }
}