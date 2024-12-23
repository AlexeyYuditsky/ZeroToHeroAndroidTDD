package ru.easycode.zerotoheroandroidtdd.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.Navigation
import ru.easycode.zerotoheroandroidtdd.core.Screen
import ru.easycode.zerotoheroandroidtdd.folder.FoldersListScreen

class MainViewModel(
    private val navigation: Navigation.Mutable
) : ViewModel() {

    fun init(firstRun: Boolean) {
        if (firstRun) navigation.update(FoldersListScreen())
    }

    fun liveData(): LiveData<Screen> = navigation.liveData()

}