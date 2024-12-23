package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel

interface ClearViewModels {
    fun clear(vararg viewModelClass: Class<out ViewModel>)
}