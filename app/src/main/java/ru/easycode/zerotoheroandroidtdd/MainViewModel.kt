package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper = LiveDataWrapper.Base(),
    private val repository: Repository = Repository.Base()
) : ViewModel() {

    fun load() = viewModelScope.launch {
        liveDataWrapper.update(UiState.ShowProgress)
        repository.load()
        liveDataWrapper.update(UiState.ShowData)
    }

    fun liveData(): LiveData<UiState> = liveDataWrapper.liveData()
}