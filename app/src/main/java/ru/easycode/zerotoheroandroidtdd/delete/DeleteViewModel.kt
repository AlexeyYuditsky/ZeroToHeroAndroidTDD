package ru.easycode.zerotoheroandroidtdd.delete

import androidx.lifecycle.LiveData
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

class DeleteViewModel(
    private val repository: Repository.Delete,
    private val itemListLiveDataWrapper: ItemListLiveDataWrapper.Mutable,
    private val itemTextLiveDataWrapper: ItemTextLiveDataWrapper.Mutable,
    private val navigation: Navigation.Update,
    private val clear: ClearViewModel,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun liveData(): LiveData<String> = itemTextLiveDataWrapper.liveData()

    fun init(itemId: Long) {
        viewModelScope.launch(dispatcherIO) {
            val item = repository.item(itemId)
            withContext(dispatcherMain) {
                itemTextLiveDataWrapper.update(item.text)
            }
        }
    }

    fun delete(itemId: Long) {
        viewModelScope.launch(dispatcherIO) {
            repository.delete(itemId)
            withContext(dispatcherMain) {
                val text = itemTextLiveDataWrapper.liveDataValue()
                val itemUi = ItemUi(id = itemId, text = text)
                itemListLiveDataWrapper.delete(itemUi)
                comeback()
            }
        }
    }

    fun comeback() {
        clear.clearViewModel(this::class.java)
        navigation.update(PopScreen(DeleteFragment::class.java))
    }

}