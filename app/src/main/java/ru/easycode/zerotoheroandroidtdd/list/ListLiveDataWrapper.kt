package ru.easycode.zerotoheroandroidtdd.list

import ru.easycode.zerotoheroandroidtdd.core.LiveDataWrapper

interface ListLiveDataWrapper {

    interface Read : LiveDataWrapper.Read<List<CharSequence>>

    interface Update : LiveDataWrapper.Update<List<CharSequence>>

    interface Mutable : Read, Update {
        fun save(bundleWrapper: BundleWrapper.Save)
    }

    interface Add {
        fun add(source: CharSequence)
    }

    interface All : Mutable, Add

    class Base : All, LiveDataWrapper.Abstract<List<CharSequence>>() {

        override fun save(bundleWrapper: BundleWrapper.Save) {
            val list = ArrayList(liveData.value ?: return)
            bundleWrapper.save(list)
        }

        override fun add(source: CharSequence) {
            val currentList = liveData.value?.plus(source) ?: return
            update(currentList)
        }
    }
}