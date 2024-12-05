package ru.easycode.zerotoheroandroidtdd.list

import kotlinx.coroutines.Dispatchers
import org.junit.Test
import ru.easycode.zerotoheroandroidtdd.FakeItemListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.FakeItemListLiveDataWrapper.Companion.LIVEDATA_UPDATE
import ru.easycode.zerotoheroandroidtdd.FakeNavigation
import ru.easycode.zerotoheroandroidtdd.Order
import ru.easycode.zerotoheroandroidtdd.core.model.Item
import ru.easycode.zerotoheroandroidtdd.core.model.ItemUi
import ru.easycode.zerotoheroandroidtdd.list.FakeRepository.Companion.REPOSITORY_LIST

class ListViewModelTest {

    private val order = Order()
    private val repository = FakeRepository.Base(order)
    private val listLiveDataWrapper = FakeItemListLiveDataWrapper.Base(order)
    private val navigation = FakeNavigation.Base(order)
    private val viewModel = ListViewModel(
        navigation = navigation,
        repository = repository,
        itemListLiveDataWrapper = listLiveDataWrapper,
        dispatcherMain = Dispatchers.Unconfined,
        dispatcherIO = Dispatchers.Unconfined,
    )

    @Test
    fun test() {
        repository.expectList(listOf(Item(id = 0L, text = "1"), Item(id = 1L, text = "2")))

        viewModel.init()

        repository.checkListCalled(
            listOf(
                Item(id = 0L, text = "1"),
                Item(id = 1L, text = "2")
            )
        )
        listLiveDataWrapper.checkUpdateCallList(
            listOf(
                ItemUi(id = 0L, text = "1"),
                ItemUi(id = 1L, text = "2")
            )
        )
        order.checkCallsList(listOf(REPOSITORY_LIST, LIVEDATA_UPDATE))
    }

}