package ru.easycode.zerotoheroandroidtdd.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.add.AddScreen
import ru.easycode.zerotoheroandroidtdd.core.ItemListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.core.Repository
import ru.easycode.zerotoheroandroidtdd.core.model.ItemUi
import ru.easycode.zerotoheroandroidtdd.core.navigation.Navigation
import ru.easycode.zerotoheroandroidtdd.delete.DeleteScreen

class ListViewModel(
    private val navigation: Navigation.Update,
    private val repository: Repository.Read,
    private val itemListLiveDataWrapper: ItemListLiveDataWrapper.Mutable,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel(), DeleteItemUi {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun delete(itemId: Long) = navigation.update(DeleteScreen(itemId))

    fun liveData(): LiveData<List<ItemUi>> = itemListLiveDataWrapper.liveData()

    fun create() = navigation.update(AddScreen())

    fun init() {
        viewModelScope.launch(dispatcherIO) {
            val itemUiList = repository.list().map { item -> ItemUi(id = item.id, item.text) }
            withContext(dispatcherMain) { itemListLiveDataWrapper.update(itemUiList) }
        }
    }

}