package ru.easycode.zerotoheroandroidtdd.list

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.easycode.zerotoheroandroidtdd.create.CreateScreen
import ru.easycode.zerotoheroandroidtdd.main.FakeNavigation

class ListViewModelTest : AbstractListViewModelTest() {

    @Test
    fun test_navigation() {
        viewModel.create()

        val expected = listOf(CreateScreen)
        val actual = navigation.fetchCreatedScreens()
        assertEquals(expected, actual)
    }

    @Test
    fun test_save_and_restore() {
        liveDataWrapper.update(listOf("1", "2", "3"))
        val bundleWrapper = FakeBundleWrapper.Base()
        val save: BundleWrapper.Save = bundleWrapper
        val restore: BundleWrapper.Restore = bundleWrapper

        // viewModel.save(bundleWrapper = save)

        val navigation = FakeNavigation.Base()
        val liveDataWrapper = FakeListLiveDataWrapper.Base()
        val viewModel = ListViewModel(
            navigation = navigation,
            liveDataWrapper = liveDataWrapper
        )

       // viewModel.restore(bundleWrapper = restore)

        val expected = listOf("1", "2", "3")
        val actual = liveDataWrapper.fetchDataList()
        assertEquals(expected, actual)
    }

}