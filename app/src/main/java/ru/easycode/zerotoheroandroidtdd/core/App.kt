package ru.easycode.zerotoheroandroidtdd.core

import android.app.Application
import androidx.lifecycle.ViewModel

class App : Application(), ProvideViewModel {

    lateinit var viewModelFactory: ProvideViewModel.Factory

    override fun onCreate() {
        super.onCreate()

        val clearViewModels = object : ClearViewModels {
            override fun clear(vararg viewModelClass: Class<out ViewModel>) {
                viewModelFactory.clear(*viewModelClass)
            }
        }

        val provideViewModel = ProvideViewModel.Provider(
            core = Core(this),
            clearViewModels = clearViewModels
        )

        viewModelFactory = ProvideViewModel.Factory(provideViewModel)
    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
        return viewModelFactory.viewModel(viewModelClass)
    }

}