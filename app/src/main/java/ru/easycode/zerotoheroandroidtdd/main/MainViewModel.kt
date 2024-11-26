package ru.easycode.zerotoheroandroidtdd.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.list.ListScreen

class MainViewModel(
    private val navigation: Navigation.Mutable
) : ViewModel(), Navigation.Read {

    fun init(value: Boolean) {
        if (value) navigation.update(ListScreen)
    }

    override fun livedata(): LiveData<Screen> = navigation.livedata()

}