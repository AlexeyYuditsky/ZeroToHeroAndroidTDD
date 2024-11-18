package ru.easycode.zerotoheroandroidtdd.main

import androidx.lifecycle.LiveData
import ru.easycode.zerotoheroandroidtdd.list.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.list.ListLiveDataWrapper

interface FakeListLiveDataWrapper : ListLiveDataWrapper.All {

    fun fetchDataList(): List<CharSequence>

    class Base : FakeListLiveDataWrapper {

        private val dataList = ArrayList<CharSequence>()

        override fun fetchDataList(): List<CharSequence> = dataList

        override fun add(source: CharSequence) {
            dataList.add(source)
        }

        override fun save(bundleWrapper: BundleWrapper.Save) {
            bundleWrapper.save(dataList)
        }

        override fun livedata(): LiveData<List<CharSequence>> {
            throw IllegalStateException("not used in tests")
        }

        override fun update(value: List<CharSequence>) {
            dataList.addAll(value)
        }

    }

}