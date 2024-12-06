package ru.easycode.zerotoheroandroidtdd

import kotlinx.coroutines.Dispatchers
import org.junit.Test
import ru.easycode.zerotoheroandroidtdd.core.Repository
import ru.easycode.zerotoheroandroidtdd.core.model.Item
import ru.easycode.zerotoheroandroidtdd.core.model.ItemUi
import ru.easycode.zerotoheroandroidtdd.list.ListViewModel

class ListViewModelTest {

    private val order = Order()
    private val repository = FakeRepository.Base()
    private val liveDataWrapper = FakeListLiveDataWrapper.Base()
    private val viewModel = ListViewModel(
        repository = repository,
        itemListLiveDataWrapper = liveDataWrapper,
        dispatcherIO = Dispatchers.Unconfined,
        dispatcherMain = Dispatchers.Unconfined,
        navigation = FakeNavigation.Base(order = order)
    )

    @Test
    fun test() {
        repository.expectList(listOf(Item(id = 0L, text = "1"), Item(id = 1L, text = "2")))

        viewModel.init()

        liveDataWrapper.checkUpdateCallList(
            listOf(
                ItemUi(id = 0L, text = "1"),
                ItemUi(id = 1L, text = "2")
            )
        )
    }
}

private interface FakeRepository : Repository.Read {

    fun expectList(list: List<Item>)

    class Base : FakeRepository {

        private val list = mutableListOf<Item>()

        override fun expectList(list: List<Item>) {
            this.list.addAll(list)
        }

        override fun list(): List<Item> {
            return list
        }

    }

}