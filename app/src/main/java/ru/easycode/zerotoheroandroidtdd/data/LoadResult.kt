package ru.easycode.zerotoheroandroidtdd.data

import ru.easycode.zerotoheroandroidtdd.data.model.SimpleResponse
import ru.easycode.zerotoheroandroidtdd.ui.LiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.ui.UiState

interface LoadResult {

    fun show(updateLiveData: LiveDataWrapper.Update)

    data class Success(
        private val data: SimpleResponse
    ) : LoadResult {
        override fun show(updateLiveData: LiveDataWrapper.Update) {
            updateLiveData.update(UiState.ShowData(data.text))
        }
    }

    data class Error(
        private val noConnection: Boolean
    ) : LoadResult {
        override fun show(updateLiveData: LiveDataWrapper.Update) {
            updateLiveData.update(
                UiState.ShowData(
                    if (noConnection) {
                        "No internet connection"
                    } else {
                        "Something went wrong"
                    }
                )
            )
        }
    }
}
