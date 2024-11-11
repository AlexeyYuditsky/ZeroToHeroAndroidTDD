package ru.easycode.zerotoheroandroidtdd.main

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.easycode.zerotoheroandroidtdd.list.ListScreen

class MainViewModelTest : AbstractMainViewModelTest() {

    @Test
    fun test_first_run() {
        viewModel.init(firstRun = true)

        val expected = listOf(ListScreen)
        val actual = navigation.fetchCreatedScreens()
        assertEquals(expected, actual)
    }

    @Test
    fun test_not_first_run() {
        viewModel.init(firstRun = false)

        val expected = emptyList<Screen>()
        val actual = navigation.fetchCreatedScreens()
        assertEquals(expected, actual)
    }

}