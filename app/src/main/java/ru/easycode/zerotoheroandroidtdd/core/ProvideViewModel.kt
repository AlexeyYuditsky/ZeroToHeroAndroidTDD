package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.list.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.list.ListViewModel
import ru.easycode.zerotoheroandroidtdd.main.MainViewModel
import ru.easycode.zerotoheroandroidtdd.main.Navigation

interface ProvideViewModel {

    fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T

    class Base : ProvideViewModel {

        private val navigation = Navigation.Base()

        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return when (viewModelClass) {
                MainViewModel::class.java -> MainViewModel(navigation)
                ListViewModel::class.java -> ListViewModel(navigation, ListLiveDataWrapper.Base())
                else -> throw IllegalArgumentException("unknown viewModel $viewModelClass")
            } as T
        }

    }

}