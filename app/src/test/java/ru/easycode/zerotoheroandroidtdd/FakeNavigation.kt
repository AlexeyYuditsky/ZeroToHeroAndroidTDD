package ru.easycode.zerotoheroandroidtdd

import org.junit.Assert.assertEquals
import ru.easycode.zerotoheroandroidtdd.core.navigation.Navigation
import ru.easycode.zerotoheroandroidtdd.core.navigation.Screen

interface FakeNavigation : Navigation.Update {

    companion object {
        const val NAVIGATION_UPDATE = "navigation#update"
    }

    fun checkUpdateCalled(expectedScreen: Screen)

    class Base(
        private val order: Order
    ) : FakeNavigation {

        private lateinit var screen: Screen

        override fun checkUpdateCalled(expectedScreen: Screen) {
            assertEquals(screen, expectedScreen)
        }

        override fun update(value: Screen) {
            order.add(NAVIGATION_UPDATE)
            screen = value
        }

    }

}