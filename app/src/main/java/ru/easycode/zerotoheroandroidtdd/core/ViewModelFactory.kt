package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel

interface ViewModelFactory : ProvideViewModel, ClearViewModel {

    class Base(
        private val provideViewModel: ProvideViewModel
    ) : ViewModelFactory {

        private val viewModelMap = mutableMapOf<Class<out ViewModel>, ViewModel>()

        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            return if (viewModelMap.containsKey(viewModelClass)) {
                viewModelMap[viewModelClass] as T
            } else {
                provideViewModel.viewModel(viewModelClass).also { viewModel ->
                    viewModelMap[viewModelClass] = viewModel
                }
            }
        }

        override fun clear(viewModelClass: Class<out ViewModel>) {
            viewModelMap.remove(viewModelClass)
        }
    }
}