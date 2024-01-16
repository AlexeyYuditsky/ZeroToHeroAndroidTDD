package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface ListLiveDataWrapper {

    fun liveData(): LiveData<List<CharSequence>>
    fun update(list: List<CharSequence>)
    fun save(bundle: BundleWrapper.Save)
    fun add(text: CharSequence)

    class Base(
        private val liveData: MutableLiveData<List<CharSequence>> = SingleLiveEvent()
    ) : ListLiveDataWrapper {

        override fun liveData(): LiveData<List<CharSequence>> = liveData

        override fun update(list: List<CharSequence>) {
            liveData.value = list
        }

        override fun save(bundle: BundleWrapper.Save) {
            val arrayList = liveData.value ?: listOf()
            update(arrayList)
        }

        override fun add(text: CharSequence) {
            val arrayList = liveData.value?.plus(text) ?: listOf(text)
            update(arrayList)
        }
    }
}