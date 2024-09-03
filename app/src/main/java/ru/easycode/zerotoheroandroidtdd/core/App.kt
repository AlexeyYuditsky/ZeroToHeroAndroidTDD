package ru.easycode.zerotoheroandroidtdd.core

import android.app.Application
import androidx.lifecycle.ViewModel

class App : Application(), ProvideViewModel {

    private val factory = ViewModelFactory.Base(ProvideViewModel.Base())

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T =
        factory.viewModel(viewModelClass)
}