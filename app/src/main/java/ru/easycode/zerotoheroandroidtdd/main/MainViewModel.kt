package ru.easycode.zerotoheroandroidtdd.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.list.ListScreen

class MainViewModel(
    private val navigation: Navigation.Mutable
) : ViewModel(), Navigation.Mutable {

    override fun livedata(): LiveData<Screen> = navigation.livedata()

    override fun update(value: Screen) = navigation.update(value)

    fun init(firstRun: Boolean) {
        if (firstRun) navigation.update(ListScreen)
    }

}