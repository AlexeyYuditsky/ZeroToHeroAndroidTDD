package ru.easycode.zerotoheroandroidtdd.main

abstract class AbstractMainViewModelTest {

    protected val navigation = FakeNavigation.Base()
    protected val viewModel = MainViewModel(navigation = navigation)

}