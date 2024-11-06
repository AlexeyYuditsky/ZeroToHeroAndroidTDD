package ru.easycode.zerotoheroandroidtdd.main

import org.junit.Test
import ru.easycode.zerotoheroandroidtdd.list.ListScreen

class MainViewModelTest {

    private val navigation = FakeNavigation.Base()
    private val viewModel = MainViewModel(navigation = navigation)

    @Test
    fun test_first_run() {
        viewModel.init(firstRun = true)
        navigation.checkUpdateCalled(listOf(ListScreen))
    }

    @Test
    fun test_not_first_run() {
        viewModel.init(firstRun = false)
        navigation.checkUpdateCalled(listOf())
    }

}