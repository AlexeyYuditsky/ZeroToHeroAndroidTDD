package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.easycode.zerotoheroandroidtdd.folder.FoldersListViewModel
import ru.easycode.zerotoheroandroidtdd.main.MainViewModel

interface ProvideViewModel {

    fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T

    class Factory(
        private val provideViewModel: ProvideViewModel
    ) : ProvideViewModel, ClearViewModels {

        private val viewModelMap = HashMap<Class<out ViewModel>, ViewModel>()

        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return viewModelMap[viewModelClass] as? T
                ?: provideViewModel.viewModel(viewModelClass)
                    .also { viewModelMap[viewModelClass] = it }
        }

        override fun clear(vararg viewModelClass: Class<out ViewModel>) {
            viewModelClass.forEach { viewModelMap.remove(it) }
        }

    }

    class Provider(
        core: Core,
        private val clearViewModels: ClearViewModels
    ) : ProvideViewModel {

        init {
            CoroutineScope(Dispatchers.IO).launch {
                core.folderDao().add(FolderCache(0, "hello"))
            }
        }

        private val navigation = Navigation.Base()

        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return when (viewModelClass) {
                MainViewModel::class.java -> MainViewModel(navigation = navigation)
                FoldersListViewModel::class.java -> FoldersListViewModel(
                    clearViewModels = clearViewModels
                )
                else -> throw IllegalArgumentException("no viewModel $viewModelClass")
            } as T
        }

    }

}