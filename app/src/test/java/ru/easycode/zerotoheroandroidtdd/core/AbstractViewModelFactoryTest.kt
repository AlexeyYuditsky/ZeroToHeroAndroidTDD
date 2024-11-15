package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel

abstract class AbstractViewModelFactoryTest {

    protected val provideViewModel = FakeProvideViewModel.Base()
    protected val viewModelFactory = ViewModelFactory.Base(provideViewModel = provideViewModel)

    protected class FakeViewModelOne : ViewModel()
    protected class FakeViewModelTwo : ViewModel()

    protected interface FakeProvideViewModel : ProvideViewModel {

        fun fetchCreatedViewModels(): List<Class<out ViewModel>>

        class Base : FakeProvideViewModel {

            private val viewModelList = mutableListOf<Class<out ViewModel>>()

            override fun fetchCreatedViewModels(): List<Class<out ViewModel>> = viewModelList

            override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
                viewModelList.add(viewModelClass)
                return viewModelClass.getDeclaredConstructor().newInstance()
            }

        }

    }

}