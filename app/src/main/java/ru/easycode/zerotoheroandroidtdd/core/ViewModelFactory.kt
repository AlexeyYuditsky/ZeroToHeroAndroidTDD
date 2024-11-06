package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel

interface ViewModelFactory : ProvideViewModel, ClearViewModel {

    class Base(
        private val provideViewModel: ProvideViewModel
    ) : ViewModelFactory {

        private val viewModelMap = mutableMapOf<Class<out ViewModel>, ViewModel>()

        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            return if (viewModelMap.containsKey(viewModelClass)) {
                @Suppress("UNCHECKED_CAST")
                viewModelMap[viewModelClass] as T
            } else {
                val viewModel = provideViewModel.viewModel(viewModelClass)
                viewModelMap[viewModelClass] = viewModel
                return viewModel
            }
        }

        override fun clear(viewModelClass: Class<out ViewModel>) {
            viewModelMap.remove(viewModelClass)
        }

    }

}