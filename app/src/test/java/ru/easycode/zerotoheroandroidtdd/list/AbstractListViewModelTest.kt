package ru.easycode.zerotoheroandroidtdd.list

import ru.easycode.zerotoheroandroidtdd.main.FakeListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.main.FakeNavigation

abstract class AbstractListViewModelTest {

    protected val navigation = FakeNavigation.Base()
    protected val liveDataWrapper = FakeListLiveDataWrapper.Base()
    protected val viewModel = ListViewModel(
        navigation = navigation,
        liveDataWrapper = liveDataWrapper
    )

}