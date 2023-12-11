package ru.easycode.zerotoheroandroidtdd.ui

import android.os.Build
import android.os.Bundle

interface BundleWrapper {

    interface Save : BundleWrapper {
        fun save(uiState: UiState)
    }

    interface Restore : BundleWrapper {
        fun restore(): UiState
    }

    interface Mutable : Save, Restore

    class Base(
        private val bundle: Bundle
    ) : Mutable {

        override fun restore(): UiState =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable(KEY, UiState::class.java) as UiState
            } else {
                bundle.getParcelable<UiState>(KEY) as UiState
            }

        override fun save(uiState: UiState) = bundle.putParcelable(KEY, uiState)
    }

    private companion object {
        const val KEY = "key"
    }
}
