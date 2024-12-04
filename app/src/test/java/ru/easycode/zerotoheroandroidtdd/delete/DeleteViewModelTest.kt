package ru.easycode.zerotoheroandroidtdd.delete

import kotlinx.coroutines.Dispatchers
import org.junit.Test
import ru.easycode.zerotoheroandroidtdd.FakeClearViewModel
import ru.easycode.zerotoheroandroidtdd.FakeClearViewModel.Companion.CLEAR_VIEWMODEL
import ru.easycode.zerotoheroandroidtdd.FakeItemListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.FakeItemListLiveDataWrapper.Companion.LIVEDATA_DELETE
import ru.easycode.zerotoheroandroidtdd.FakeNavigation
import ru.easycode.zerotoheroandroidtdd.FakeNavigation.Companion.NAVIGATION_UPDATE
import ru.easycode.zerotoheroandroidtdd.Order
import ru.easycode.zerotoheroandroidtdd.core.model.Item
import ru.easycode.zerotoheroandroidtdd.core.model.ItemUi
import ru.easycode.zerotoheroandroidtdd.core.navigation.PopScreen
import ru.easycode.zerotoheroandroidtdd.delete.FakeItemTextWrapper.Companion.LIVEDATA_ADD
import ru.easycode.zerotoheroandroidtdd.delete.FakeItemTextWrapper.Companion.LIVEDATA_VALUE
import ru.easycode.zerotoheroandroidtdd.delete.FakeRepositoryDelete.Companion.REPOSITORY_DELETE
import ru.easycode.zerotoheroandroidtdd.delete.FakeRepositoryDelete.Companion.REPOSITORY_ITEM

class DeleteViewModelTest {

    private val order = Order()
    private val itemListLiveDataWrapper = FakeItemListLiveDataWrapper.Base(order)
    private val itemTextLiveDataWrapper = FakeItemTextWrapper.Base(order)
    private val repository = FakeRepositoryDelete.Base(order)
    private val clear = FakeClearViewModel.Base(order)
    private val navigation = FakeNavigation.Base(order)
    private val viewModel = DeleteViewModel(
        itemListLiveDataWrapper = itemListLiveDataWrapper,
        itemTextLiveDataWrapper = itemTextLiveDataWrapper,
        repository = repository,
        clear = clear,
        navigation = navigation,
        dispatcherIO = Dispatchers.Unconfined,
        dispatcherMain = Dispatchers.Unconfined
    )

    @Test
    fun test_init() {
        viewModel.init(itemId = 5L)

        repository.checkItemCalled(Item(5L, "5"))
        itemTextLiveDataWrapper.checkUpdateCalled("5")
        order.checkCallsList(listOf(REPOSITORY_ITEM, LIVEDATA_ADD))
    }

    @Test
    fun test_delete() {
        itemListLiveDataWrapper.update(
            listOf(
                ItemUi(id = 8L, text = "8"),
                ItemUi(id = 9L, text = "any")
            )
        )
        viewModel.init(itemId = 8L)
        viewModel.delete(itemId = 8L)

        repository.checkItemCalled(Item(id = 8L, text = "8"))
        itemTextLiveDataWrapper.checkUpdateCalled("8")
        repository.checkDeleteCalled(8L)
        itemTextLiveDataWrapper.checkUpdateCalled("8")
        itemListLiveDataWrapper.checkUpdateCallList(listOf(ItemUi(id = 9L, text = "any")))
        clear.checkClearCalled(DeleteViewModel::class.java)
        navigation.checkUpdateCalled(PopScreen(DeleteFragment::class.java))
        order.checkCallsList(
            listOf(
                REPOSITORY_ITEM,
                LIVEDATA_ADD,
                REPOSITORY_DELETE,
                LIVEDATA_VALUE,
                LIVEDATA_DELETE,
                CLEAR_VIEWMODEL,
                NAVIGATION_UPDATE
            )
        )
    }

    @Test
    fun test_comeback() {
        viewModel.comeback()

        clear.checkClearCalled(DeleteViewModel::class.java)
        navigation.checkUpdateCalled(PopScreen(DeleteFragment::class.java))
        order.checkCallsList(listOf(CLEAR_VIEWMODEL, NAVIGATION_UPDATE))
    }

}

