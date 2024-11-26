package ru.easycode.zerotoheroandroidtdd.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.easycode.zerotoheroandroidtdd.add.AddScreen
import ru.easycode.zerotoheroandroidtdd.core.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.core.Repository
import ru.easycode.zerotoheroandroidtdd.core.log
import ru.easycode.zerotoheroandroidtdd.main.Navigation

class ListViewModel(
    private val repository: Repository.Read,
    private val liveDataWrapper: ListLiveDataWrapper.Mutable,
    private val navigation: Navigation.Mutable,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel(), ListLiveDataWrapper.Read {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun livedata(): LiveData<List<String>> = liveDataWrapper.livedata()

    fun init() {
        viewModelScope.launch(dispatcher) {
            val value = repository.list()
            liveDataWrapper.update(value)
        }
    }

    fun create() = navigation.update(AddScreen)

}