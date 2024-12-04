package ru.easycode.zerotoheroandroidtdd.add

import kotlinx.coroutines.Dispatchers
import org.junit.Test
import ru.easycode.zerotoheroandroidtdd.FakeClearViewModel
import ru.easycode.zerotoheroandroidtdd.FakeClearViewModel.Companion.CLEAR_VIEWMODEL
import ru.easycode.zerotoheroandroidtdd.FakeNavigation
import ru.easycode.zerotoheroandroidtdd.FakeNavigation.Companion.NAVIGATION_UPDATE
import ru.easycode.zerotoheroandroidtdd.Order
import ru.easycode.zerotoheroandroidtdd.add.FakeAddLiveDataWrapper.Companion.LIVEDATA_ADD
import ru.easycode.zerotoheroandroidtdd.add.FakeAddRepository.Companion.REPOSITORY_ADD
import ru.easycode.zerotoheroandroidtdd.core.model.ItemUi
import ru.easycode.zerotoheroandroidtdd.core.navigation.PopScreen

class AddViewModelTest {

    private val order = Order()
    private val repository = FakeAddRepository.Base(order)
    private val itemListLiveDataWrapper = FakeAddLiveDataWrapper.Base(order)
    private val clear = FakeClearViewModel.Base(order)
    private val navigation = FakeNavigation.Base(order)
    private val viewModel = AddViewModel(
        navigation = navigation,
        repository = repository,
        itemListLiveDataWrapper = itemListLiveDataWrapper,
        clear = clear,
        dispatcherIO = Dispatchers.Unconfined,
        dispatcherMain = Dispatchers.Unconfined
    )

    @Test
    fun test_add() {
        viewModel.add(value = "new text input")

        repository.checkAddCalled("new text input")
        itemListLiveDataWrapper.checkAddCalled(ItemUi(id = 10L, text = "new text input"))
        clear.checkClearCalled(AddViewModel::class.java)
        navigation.checkUpdateCalled(PopScreen(AddFragment::class.java))
        order.checkCallsList(
            listOf(REPOSITORY_ADD, LIVEDATA_ADD, CLEAR_VIEWMODEL, NAVIGATION_UPDATE)
        )
    }

    @Test
    fun test_comeback() {
        viewModel.comeback()

        clear.checkClearCalled(AddViewModel::class.java)
        navigation.checkUpdateCalled(PopScreen(AddFragment::class.java))
        order.checkCallsList(listOf(CLEAR_VIEWMODEL, NAVIGATION_UPDATE))
    }

}