package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.add.AddViewModel
import ru.easycode.zerotoheroandroidtdd.list.ListViewModel
import ru.easycode.zerotoheroandroidtdd.main.MainViewModel
import ru.easycode.zerotoheroandroidtdd.main.Navigation

interface ProvideViewModel {

    fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T

    class Base(
        core: Core,
        private val clearViewModel: ClearViewModel
    ) : ProvideViewModel {

        private val repository = Repository.Base(
            dataSource = core.dao(),
            now = Now.Base()
        )

        private val navigation = Navigation.Base()
        private val liveDataWrapper = ListLiveDataWrapper.Base()

        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return when (viewModelClass) {
                MainViewModel::class.java -> MainViewModel(navigation)
                ListViewModel::class.java -> ListViewModel(repository, liveDataWrapper, navigation)
                AddViewModel::class.java -> AddViewModel(
                    repository,
                    liveDataWrapper,
                    navigation,
                    clearViewModel
                )

                else -> throw IllegalArgumentException("unknown viewModel $viewModelClass")
            } as T
        }

    }

}