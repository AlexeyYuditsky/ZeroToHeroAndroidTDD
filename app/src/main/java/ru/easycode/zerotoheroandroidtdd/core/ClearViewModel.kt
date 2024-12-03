package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel

interface ClearViewModel {
    fun clearViewModel(viewModelClass: Class<out ViewModel>)
}