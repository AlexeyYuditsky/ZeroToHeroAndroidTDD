package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface LiveDataWrapper {

    interface Update<T> {
        fun update(value: T)
    }

    interface Read<T> {
        fun liveData(): LiveData<T>
    }

    interface Mutable<T> : Update<T>, Read<T>

    abstract class Abstract<T>(
        private val liveData: MutableLiveData<T> = SingleLiveEvent()
    ) : Mutable<T> {

        override fun update(value: T) {
            liveData.value = value
        }

        override fun liveData(): LiveData<T> = liveData

    }

}