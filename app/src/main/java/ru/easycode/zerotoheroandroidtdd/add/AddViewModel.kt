package ru.easycode.zerotoheroandroidtdd.add

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.core.ItemListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.core.Repository
import ru.easycode.zerotoheroandroidtdd.core.model.ItemUi
import ru.easycode.zerotoheroandroidtdd.core.navigation.Navigation
import ru.easycode.zerotoheroandroidtdd.core.navigation.PopScreen

class AddViewModel(
    private val repository: Repository.Add,
    private val itemListLiveDataWrapper: ItemListLiveDataWrapper.Add,
    private val navigation: Navigation.Update,
    private val clear: ClearViewModel,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun add(value: String) {
        viewModelScope.launch(dispatcherIO) {
            val id = repository.add(value)
            val itemUi = ItemUi(id, value)
            withContext(dispatcherMain) {
                itemListLiveDataWrapper.add(itemUi)
                comeback()
            }
        }
    }

    fun comeback() {
        clear.clearViewModel(this::class.java)
        navigation.update(PopScreen(AddFragment::class.java))
    }

}