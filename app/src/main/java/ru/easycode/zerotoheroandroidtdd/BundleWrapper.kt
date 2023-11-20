package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle
import java.io.Serializable

interface BundleWrapper : Serializable {

    interface Restore : BundleWrapper {
        fun restore(): UiState
    }

    interface Save : BundleWrapper {
        fun save(uiState: UiState)
    }

    interface Mutable : Save, Restore

    class Base(
        private val bundle: Bundle
    ) : Mutable {

        override fun save(uiState: UiState) = bundle.putSerializable(KEY, uiState)

        override fun restore(): UiState =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getSerializable(KEY, UiState::class.java) as UiState
            } else {
                bundle.getSerializable(KEY) as UiState
            }
    }

    private companion object {
        const val KEY = "uiStateKey"
    }
}