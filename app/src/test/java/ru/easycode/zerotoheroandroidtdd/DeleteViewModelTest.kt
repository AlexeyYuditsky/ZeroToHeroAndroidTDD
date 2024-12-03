package ru.easycode.zerotoheroandroidtdd

import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertEquals
import org.junit.Test
import ru.easycode.zerotoheroandroidtdd.FakeClearViewModel.Companion.CLEAR
import ru.easycode.zerotoheroandroidtdd.FakeItemListLiveDataWrapper.Companion.LIVE_DATA_DELETE
import ru.easycode.zerotoheroandroidtdd.FakeRepositoryDelete.Companion.REPOSITORY_DELETE
import ru.easycode.zerotoheroandroidtdd.core.Repository
import ru.easycode.zerotoheroandroidtdd.core.model.Item
import ru.easycode.zerotoheroandroidtdd.core.model.ItemUi
import ru.easycode.zerotoheroandroidtdd.core.navigation.Navigation
import ru.easycode.zerotoheroandroidtdd.delete.DeleteViewModel

class DeleteViewModelTest {

    private val order = Order()
    private val itemListLiveDataWrapper = FakeItemListLiveDataWrapper.Base(order)
    private val itemTextLiveDataWrapper = FakeItemTextLiveDataWrapper.Base(order)
    private val repository = FakeRepositoryDelete.Base(order)
    private val clear = FakeClearViewModel.Base(order)
    private val navigation = Navigation.Base()
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

        itemTextLiveDataWrapper.checkUpdateCalled("5")
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

        repository.checkDeleteCalled(8L)
        itemListLiveDataWrapper.checkUpdateCallList(listOf(ItemUi(id = 9L, text = "any")))
        clear.checkClearCalled(DeleteViewModel::class.java)
        order.checkCallsList(listOf(REPOSITORY_DELETE, LIVE_DATA_DELETE, CLEAR))
    }

    @Test
    fun test_comeback() {
        viewModel.comeback()
        clear.checkClearCalled(DeleteViewModel::class.java)
    }
}

private interface FakeRepositoryDelete : Repository.Delete {

    companion object {
        const val REPOSITORY_DELETE = "Repository.Delete#delete"
    }

    fun checkDeleteCalled(id: Long)

    class Base(
        private val order: Order
    ) : FakeRepositoryDelete {

        private var actualId: Long = Long.MIN_VALUE

        override fun item(id: Long): Item {
            return Item(id, id.toString())
        }

        override fun checkDeleteCalled(id: Long) {
            assertEquals(id, actualId)
        }

        override fun delete(id: Long) {
            order.add(REPOSITORY_DELETE)
            actualId = id
        }
    }
}