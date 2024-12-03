package ru.easycode.zerotoheroandroidtdd

import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertEquals
import org.junit.Test
import ru.easycode.zerotoheroandroidtdd.FakeClearViewModel.Companion.CLEAR
import ru.easycode.zerotoheroandroidtdd.add.AddViewModel
import ru.easycode.zerotoheroandroidtdd.core.ItemListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.core.Repository
import ru.easycode.zerotoheroandroidtdd.core.model.ItemUi

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
        order.checkCallsList(listOf(REPOSITORY_ADD, LIVEDATA_ADD, CLEAR))
    }

    @Test
    fun test_comeback() {
        viewModel.comeback()

        clear.checkClearCalled(AddViewModel::class.java)
        order.checkCallsList(listOf(CLEAR))
    }

}

class Order {

    private val list = mutableListOf<String>()

    fun add(value: String) {
        list.add(value)
    }

    fun checkCallsList(expected: List<String>) {
        assertEquals(expected, list)
    }

}

private interface FakeAddLiveDataWrapper : ItemListLiveDataWrapper.Add {

    fun checkAddCalled(expected: ItemUi)

    class Base(
        private val order: Order
    ) : FakeAddLiveDataWrapper {

        private var actual = ItemUi(Long.MIN_VALUE, "")

        override fun checkAddCalled(expected: ItemUi) {
            assertEquals(expected, actual)
        }

        override fun add(item: ItemUi) {
            order.add(LIVEDATA_ADD)
            actual = item
        }

    }

}

private interface FakeAddRepository : Repository.Add {

    fun checkAddCalled(expected: String)

    class Base(private val order: Order) : FakeAddRepository {

        private var actual = ""
        private var count = 10L

        override fun checkAddCalled(expected: String) {
            assertEquals(expected, actual)
        }

        override fun add(value: String): Long {
            order.add(REPOSITORY_ADD)
            actual = value
            return count++
        }

    }

}

private const val REPOSITORY_ADD = "repository#add"
private const val LIVEDATA_ADD = "liveData#add"