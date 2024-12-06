package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertEquals
import org.junit.Test
import ru.easycode.zerotoheroandroidtdd.FakeClearViewModel.Companion.CLEAR
import ru.easycode.zerotoheroandroidtdd.FakeListLiveDataWrapper.Companion.LIVE_DATA_DELETE
import ru.easycode.zerotoheroandroidtdd.FakeListLiveDataWrapper.Companion.LIVE_DATA_UPDATE
import ru.easycode.zerotoheroandroidtdd.FakeRepositoryChange.Companion.REPOSITORY_DELETE
import ru.easycode.zerotoheroandroidtdd.FakeRepositoryChange.Companion.REPOSITORY_UPDATE
import ru.easycode.zerotoheroandroidtdd.core.Repository
import ru.easycode.zerotoheroandroidtdd.core.model.Item
import ru.easycode.zerotoheroandroidtdd.core.model.ItemUi
import ru.easycode.zerotoheroandroidtdd.details.DetailsViewModel
import ru.easycode.zerotoheroandroidtdd.details.ItemTextLiveDataWrapper

class DetailsViewModelTest {

    private val order = Order()
    private val repository = FakeRepositoryChange.Base(order)
    private val liveDataWrapper = FakeListLiveDataWrapper.Base(order)
    private val clear = FakeClearViewModel.Base(order)
    private val navigation = FakeNavigation.Base(order)
    private val itemTextLiveDataWrapper = FakeItemTextLiveDataWrapper.Base(order)
    private val viewModel = DetailsViewModel(
        repository = repository,
        itemListLiveDataWrapper = liveDataWrapper,
        clear = clear,
        dispatcherIO = Dispatchers.Unconfined,
        dispatcherMain = Dispatchers.Unconfined,
        navigation = navigation,
        itemTextLiveDataWrapper = itemTextLiveDataWrapper
    )

    @Test
    fun test_init() {
        viewModel.init(itemId = 5L)

        itemTextLiveDataWrapper.checkLiveDataValue("5")
    }

    @Test
    fun test_delete() {
        liveDataWrapper.update(listOf(ItemUi(id = 8L, text = "8"), ItemUi(id = 9L, text = "any")))
        viewModel.init(itemId = 8L)

        viewModel.delete(itemId = 8L)
        repository.checkDeleteCalled(8L)
        liveDataWrapper.checkUpdateCallList(listOf(ItemUi(id = 9L, text = "any")))
        clear.checkClearCalled(DetailsViewModel::class.java)
        order.checkCallsList(listOf(REPOSITORY_DELETE, LIVE_DATA_DELETE, CLEAR))
    }

    @Test
    fun test_update() {
        liveDataWrapper.update(listOf(ItemUi(id = 8L, text = "8"), ItemUi(id = 9L, text = "any")))
        viewModel.init(itemId = 8L)

        viewModel.update(id = 8L, newText = "newText")
        repository.checkUpdateCalled(8L, "newText")
        liveDataWrapper.checkUpdateCallList(
            listOf(
                ItemUi(id = 8L, text = "newText"),
                ItemUi(id = 9L, text = "any")
            )
        )
        clear.checkClearCalled(DetailsViewModel::class.java)
        order.checkCallsList(listOf(REPOSITORY_UPDATE, LIVE_DATA_UPDATE, CLEAR))
    }

    @Test
    fun test_comeback() {
        viewModel.comeback()
        clear.checkClearCalled(DetailsViewModel::class.java)
    }
}

private interface FakeRepositoryChange : Repository.Change {

    companion object {
        const val REPOSITORY_DELETE = "Repository.Delete#delete"
        const val REPOSITORY_UPDATE = "Repository.Delete#update"
    }

    fun checkDeleteCalled(id: Long)

    fun checkUpdateCalled(id: Long, newText: String)

    class Base(private val order: Order = Order()) : FakeRepositoryChange {

        private var actualId: Long = Long.MIN_VALUE
        private var updateText: String = ""

        override fun item(id: Long): Item {
            return Item(id, id.toString())
        }

        override fun checkDeleteCalled(id: Long) {
            assertEquals(id, actualId)
        }

        override fun checkUpdateCalled(id: Long, newText: String) {
            assertEquals(updateText, newText)
            assertEquals(id, actualId)
        }

        override fun delete(id: Long) {
            order.add(REPOSITORY_DELETE)
            actualId = id
        }

        override fun update(id: Long, newText: String) {
            updateText = newText
            actualId = id
            order.add(REPOSITORY_UPDATE)
        }
    }
}

private interface FakeItemTextLiveDataWrapper : ItemTextLiveDataWrapper.Mutable {

    fun checkLiveDataValue(expected: String)

    companion object {
        const val LIVEDATA_VALUE = "itemTextLiveDataWrapper#liveDataValue"
        const val LIVEDATA_UPDATE = "itemTextLiveDataWrapper#update"
    }

    class Base(
        private val order: Order
    ) : FakeItemTextLiveDataWrapper {

        private lateinit var value: String

        override fun update(value: String) {
            order.add(LIVEDATA_UPDATE)
            this.value = value
        }

        override fun liveData(): LiveData<String> = throw IllegalArgumentException("not used")

        override fun liveDataValue(): String {
            order.add(LIVEDATA_VALUE)
            return value
        }

        override fun checkLiveDataValue(expected: String) {
            assertEquals(expected, value)
        }
    }

}