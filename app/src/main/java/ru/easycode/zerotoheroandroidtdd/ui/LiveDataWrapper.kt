package ru.easycode.zerotoheroandroidtdd.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface LiveDataWrapper {

    interface Update : LiveDataWrapper

    interface Mutable : Update

    fun save(bundleWrapper: BundleWrapper.Save)

    fun update(value: UiState)

    fun liveData(): LiveData<UiState>

    class Base(
        private val singleLiveEvent: MutableLiveData<UiState> = SingleLiveEvent()
    ) : Mutable {

        override fun save(bundleWrapper: BundleWrapper.Save) {
            singleLiveEvent.value?.let { uiState ->
                bundleWrapper.save(uiState)
            }
        }

        override fun update(value: UiState) {
            singleLiveEvent.value = value
        }

        override fun liveData(): LiveData<UiState> = singleLiveEvent
    }
}