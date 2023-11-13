package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData

interface ProvideLiveData {
    fun liveData(): LiveData<UiState>
}