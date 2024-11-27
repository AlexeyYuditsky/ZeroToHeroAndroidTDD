package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface ListLiveDataWrapper {

    interface Read {
        fun livedata(): LiveData<List<String>>
    }

    interface Update {
        fun update(value: List<String>)
    }

    interface Add {
        fun add(value: String)
    }

    interface Mutable : Update, Read

    interface All : Mutable, Add

    class Base : All {

        private val liveData = MutableLiveData<List<String>>()

        override fun livedata(): LiveData<List<String>> = liveData

        override fun update(value: List<String>) {
            liveData.value = value
        }

        override fun add(value: String) {
            val currentList = liveData.value?.plus(value) ?: listOf(value)
            update(currentList)
        }

    }

}