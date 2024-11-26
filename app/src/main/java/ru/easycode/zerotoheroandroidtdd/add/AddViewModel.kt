package ru.easycode.zerotoheroandroidtdd.add

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.core.Repository
import ru.easycode.zerotoheroandroidtdd.core.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.main.Navigation
import ru.easycode.zerotoheroandroidtdd.main.Screen

class AddViewModel(
    private val repository: Repository.Add,
    private val liveDataWrapper: ListLiveDataWrapper.Add,
    private val navigation: Navigation.Update,
    private val clear: ClearViewModel,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun add(value: String) {
        viewModelScope.launch(dispatcher) {
            repository.add(value)
            withContext(dispatcherMain) {
                liveDataWrapper.add(value)
                comeback()
            }
        }
    }

    fun comeback() {
        clear.clearViewModel(this::class.java)
        navigation.update(Screen.PopBottomSheet)
    }

}