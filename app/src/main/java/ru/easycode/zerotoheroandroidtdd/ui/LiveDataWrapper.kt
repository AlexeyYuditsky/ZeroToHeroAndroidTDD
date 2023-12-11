package ru.easycode.zerotoheroandroidtdd.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface LiveDataWrapper<T> {

    fun update(uiState: T)
    fun liveData(): LiveData<T>
    fun save(bundleWrapper: BundleWrapper.Save)

    class Base(
        private val liveData: MutableLiveData<UiState> = SingleLiveEvent()
    ) : LiveDataWrapper<UiState> {
        override fun update(uiState: UiState) {
            liveData.value = uiState
        }

        override fun liveData(): LiveData<UiState> = liveData

        override fun save(bundleWrapper: BundleWrapper.Save) {
            liveData.value?.let { uiState -> bundleWrapper.save(uiState) }
        }
    }
}
