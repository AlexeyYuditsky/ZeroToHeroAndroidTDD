package ru.easycode.zerotoheroandroidtdd

import android.app.Application

class App : Application() {
    val mainViewModel = MainViewModel(LiveDataWrapper.Base(), Repository.Base())
}