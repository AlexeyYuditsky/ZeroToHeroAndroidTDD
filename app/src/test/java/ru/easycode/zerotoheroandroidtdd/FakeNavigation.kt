package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import ru.easycode.zerotoheroandroidtdd.core.navigation.Navigation
import ru.easycode.zerotoheroandroidtdd.core.navigation.Screen

interface FakeNavigation : Navigation.Mutable {

    fun fetchCreatedScreens(): List<Screen>

    class Base(
        private val order: Order
    ) : FakeNavigation {

        private val createdScreenList = mutableListOf<Screen>()

        override fun fetchCreatedScreens() = createdScreenList

        override fun liveData(): LiveData<Screen> = throw IllegalStateException("not used")

        override fun update(value: Screen) {
            createdScreenList.add(value)
        }

    }

}