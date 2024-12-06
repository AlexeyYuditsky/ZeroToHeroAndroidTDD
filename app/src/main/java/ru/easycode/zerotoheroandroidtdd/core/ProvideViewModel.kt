package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.add.AddViewModel
import ru.easycode.zerotoheroandroidtdd.core.navigation.Navigation
import ru.easycode.zerotoheroandroidtdd.details.DetailsViewModel
import ru.easycode.zerotoheroandroidtdd.details.ItemTextLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.list.ListViewModel
import ru.easycode.zerotoheroandroidtdd.main.MainViewModel

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
        private val liveDataWrapper = ItemListLiveDataWrapper.Base()

        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return when (viewModelClass) {
                MainViewModel::class.java -> MainViewModel(navigation)
                ListViewModel::class.java -> ListViewModel(navigation, repository, liveDataWrapper)
                AddViewModel::class.java -> AddViewModel(
                    repository,
                    liveDataWrapper,
                    navigation,
                    clearViewModel
                )

                DetailsViewModel::class.java -> DetailsViewModel(
                    repository,
                    liveDataWrapper,
                    ItemTextLiveDataWrapper.Base(),
                    navigation,
                    clearViewModel
                )

                else -> throw IllegalArgumentException("unknown viewModel $viewModelClass")
            } as T
        }

    }

}