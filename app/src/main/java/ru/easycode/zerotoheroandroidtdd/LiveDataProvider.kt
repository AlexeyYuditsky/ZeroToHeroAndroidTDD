package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData

interface LiveDataProvider {
    fun liveData(): LiveData<UiState>
}