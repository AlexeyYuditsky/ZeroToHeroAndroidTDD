package ru.easycode.zerotoheroandroidtdd

import kotlinx.coroutines.Dispatchers
import org.junit.Test
import ru.easycode.zerotoheroandroidtdd.core.Repository
import ru.easycode.zerotoheroandroidtdd.core.model.Item
import ru.easycode.zerotoheroandroidtdd.core.model.ItemUi
import ru.easycode.zerotoheroandroidtdd.core.navigation.Navigation
import ru.easycode.zerotoheroandroidtdd.list.ListViewModel

class ListViewModelTest {

    @Test
    fun test() {
        val repository = FakeRepository.Base()
        val liveDataWrapper = FakeItemListLiveDataWrapper.Base()
        val navigation = Navigation.Base()
        val viewModel = ListViewModel(
            navigation = navigation,
            repository = repository,
            listLiveDataWrapper = liveDataWrapper,
            dispatcherMain = Dispatchers.Unconfined,
            dispatcherIO = Dispatchers.Unconfined,
        )

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