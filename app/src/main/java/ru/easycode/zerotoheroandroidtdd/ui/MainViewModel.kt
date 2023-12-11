package ru.easycode.zerotoheroandroidtdd.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.data.dataSource.SimpleService
import ru.easycode.zerotoheroandroidtdd.data.repository.Repository

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper<UiState> = LiveDataWrapper.Base(),
    private val repository: Repository = Repository.Base(
        SimpleService.getService(),
        SimpleService.URL_PATH
    )
) : ViewModel() {

    fun load() {
        liveDataWrapper.update(UiState.ShowProgress)
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.load().text
            withContext(Dispatchers.Main) {
                liveDataWrapper.update(UiState.ShowData(response))
            }
        }
    }

    fun liveData(): LiveData<UiState> = liveDataWrapper.liveData()

    fun save(bundleWrapper: BundleWrapper.Save) = liveDataWrapper.save(bundleWrapper)

    fun restore(bundleWrapper: BundleWrapper.Restore) {
        val uiState = bundleWrapper.restore()
        liveDataWrapper.update(uiState)
    }
}