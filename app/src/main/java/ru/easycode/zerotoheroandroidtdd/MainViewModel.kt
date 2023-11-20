package ru.easycode.zerotoheroandroidtdd

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper = LiveDataWrapper.Base(),
    private val repository: Repository = Repository.Base()
) : ViewModel(), LiveDataProvider {

    override fun liveData(): LiveData<UiState> = liveDataWrapper.liveData()

    fun load() {
        liveDataWrapper.update(UiState.ShowProgress)
        viewModelScope.launch {
            repository.load()
            liveDataWrapper.update(UiState.ShowData)
        }
    }

    fun save(bundleWrapper: BundleWrapper.Save) = liveDataWrapper.save(bundleWrapper)

    fun restore(bundleWrapper: BundleWrapper.Restore) {
        val uiState = bundleWrapper.restore()
        liveDataWrapper.update(uiState)
    }

    override fun onCleared() {
        Log.d("MyLog", "onCleared")
        super.onCleared()
    }
}
