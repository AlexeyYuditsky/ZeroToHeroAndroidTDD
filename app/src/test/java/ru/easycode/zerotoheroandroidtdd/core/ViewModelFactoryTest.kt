package ru.easycode.zerotoheroandroidtdd.core

import org.junit.Assert.assertEquals
import org.junit.Test

class ViewModelFactoryTest : AbstractViewModelFactoryTest() {

    @Test
    fun test_cached_same() {
        viewModelFactory.viewModel(viewModelClass = FakeViewModelOne::class.java)
        viewModelFactory.viewModel(viewModelClass = FakeViewModelOne::class.java)

        val expected = listOf(FakeViewModelOne::class.java)
        val actual = provideViewModel.fetchCreatedViewModels()
        assertEquals(expected, actual)
    }

    @Test
    fun test_called_other() {
        viewModelFactory.viewModel(viewModelClass = FakeViewModelOne::class.java)
        viewModelFactory.viewModel(viewModelClass = FakeViewModelTwo::class.java)

        val expected = listOf(FakeViewModelOne::class.java, FakeViewModelTwo::class.java)
        val actual = provideViewModel.fetchCreatedViewModels()
        assertEquals(expected, actual)
    }

    @Test
    fun test_clear_first() {
        viewModelFactory.viewModel(viewModelClass = FakeViewModelOne::class.java)
        viewModelFactory.viewModel(viewModelClass = FakeViewModelTwo::class.java)
        viewModelFactory.clear(viewModelClass = FakeViewModelOne::class.java)
        viewModelFactory.viewModel(viewModelClass = FakeViewModelOne::class.java)

        val expected = listOf(
            FakeViewModelOne::class.java,
            FakeViewModelTwo::class.java,
            FakeViewModelOne::class.java
        )
        val actual = provideViewModel.fetchCreatedViewModels()
        assertEquals(expected, actual)
    }

    @Test
    fun test_clear_second() {
        viewModelFactory.viewModel(viewModelClass = FakeViewModelOne::class.java)
        viewModelFactory.viewModel(viewModelClass = FakeViewModelTwo::class.java)
        viewModelFactory.clear(viewModelClass = FakeViewModelTwo::class.java)
        viewModelFactory.viewModel(viewModelClass = FakeViewModelTwo::class.java)

        val expected = listOf(
            FakeViewModelOne::class.java,
            FakeViewModelTwo::class.java,
            FakeViewModelTwo::class.java
        )
        val actual = provideViewModel.fetchCreatedViewModels()
        assertEquals(expected, actual)
    }

}