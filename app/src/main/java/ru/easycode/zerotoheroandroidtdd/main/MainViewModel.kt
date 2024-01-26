package ru.easycode.zerotoheroandroidtdd.main

import ru.easycode.zerotoheroandroidtdd.list.ListScreen

class MainViewModel(
    private val navigation: Navigation.Mutable
) : Navigation.Update { // todo viewmodel android

    override fun update(value: Screen) = navigation.update(value)

    fun init(firstRun: Boolean) {
        if (firstRun) navigation.update(ListScreen)
    }
}