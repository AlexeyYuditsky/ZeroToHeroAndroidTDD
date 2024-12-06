package ru.easycode.zerotoheroandroidtdd

import org.junit.Assert.assertEquals
import ru.easycode.zerotoheroandroidtdd.core.navigation.Navigation
import ru.easycode.zerotoheroandroidtdd.core.navigation.Screen

interface FakeNavigation : Navigation.Update {

    companion object {
        const val NAVIGATION_UPDATE = "navigation#update"
    }

    fun checkUpdateCalled(expected: Screen)

    class Base(
        private val order: Order
    ) : FakeNavigation {

        private lateinit var screen: Screen

        override fun update(value: Screen) {
            order.add(NAVIGATION_UPDATE)
            screen = value
        }

        override fun checkUpdateCalled(expected: Screen) {
            assertEquals(expected, screen)
        }

    }

}