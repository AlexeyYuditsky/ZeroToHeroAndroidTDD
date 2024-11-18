package ru.easycode.zerotoheroandroidtdd.create

import androidx.lifecycle.ViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.list.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.main.FakeListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.main.FakeNavigation
import ru.easycode.zerotoheroandroidtdd.main.Navigation
import ru.easycode.zerotoheroandroidtdd.main.Screen

class CreateViewModelTest {

    private lateinit var viewModel: CreateViewModel
    private lateinit var addLiveDataWrapper: FakeListLiveDataWrapper
    private lateinit var navigation: FakeNavigation
    private lateinit var clearViewModel: FakeClearViewModel

    @Before
    fun setup() {
        addLiveDataWrapper = FakeListLiveDataWrapper.Base()
        val add: ListLiveDataWrapper.Add = addLiveDataWrapper

        navigation = FakeNavigation.Base()
        val navigationUpdate: Navigation.Update = navigation

        clearViewModel = FakeClearViewModel.Base()
        viewModel = CreateViewModel(
            addLiveDataWrapper = add,
            navigation = navigationUpdate,
            clearViewModel = clearViewModel
        )
    }

    @Test
    fun test_add() {
        viewModel.add(text = "exampleText")

        val actualLiveData = addLiveDataWrapper.fetchDataList()
        val expectedLiveData = listOf("exampleText")
        assertEquals(expectedLiveData, actualLiveData)

        val actualNavigation = navigation.fetchCreatedScreens()
        val expectedNavigation = listOf(Screen.Pop)
        assertEquals(expectedNavigation, actualNavigation)

        val actualClear = clearViewModel.fetchClearViewModel()
        val expectedClear = CreateViewModel::class.java
        assertEquals(expectedClear, actualClear)
    }

    @Test
    fun test_comeback() {
        viewModel.comeback()

        val actualNavigation = navigation.fetchCreatedScreens()
        val expectedNavigation = listOf(Screen.Pop)
        assertEquals(expectedNavigation, actualNavigation)

        val actualClear = clearViewModel.fetchClearViewModel()
        val expectedClear = CreateViewModel::class.java
        assertEquals(expectedClear, actualClear)
    }

}

private interface FakeClearViewModel : ClearViewModel {

    fun fetchClearViewModel(): Class<out ViewModel>

    class Base : FakeClearViewModel {

        private lateinit var actual: Class<out ViewModel>

        override fun clear(viewModelClass: Class<out ViewModel>) {
            actual = viewModelClass
        }

        override fun fetchClearViewModel(): Class<out ViewModel> = actual

    }

}
