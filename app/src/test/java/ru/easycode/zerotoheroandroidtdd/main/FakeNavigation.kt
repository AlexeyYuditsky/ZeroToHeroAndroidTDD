package ru.easycode.zerotoheroandroidtdd.main

import androidx.lifecycle.LiveData

interface FakeNavigation : Navigation.Mutable {

    fun fetchCreatedScreens(): List<Screen>

    class Base : FakeNavigation {

        private val createdScreenList = mutableListOf<Screen>()

        override fun fetchCreatedScreens() = createdScreenList

        override fun update(value: Screen) {
            createdScreenList.add(value)
        }

        override fun livedata(): LiveData<Screen> = throw IllegalStateException("not used")

    }

}