package ru.easycode.zerotoheroandroidtdd.core

import android.app.Application
import androidx.lifecycle.ViewModel

class App : Application(), ProvideViewModel {

    private lateinit var factory: ViewModelFactory

    override fun onCreate() {
        super.onCreate()
        val core = Core(this)

        val clear: ClearViewModel = object : ClearViewModel {
            override fun clearViewModel(viewModelClass: Class<out ViewModel>) = factory.clearViewModel(viewModelClass)
        }
        val provideViewModel = ProvideViewModel.Base(core, clear)
        factory = ViewModelFactory.Base(provideViewModel)
    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
        return factory.viewModel(viewModelClass)
    }

}