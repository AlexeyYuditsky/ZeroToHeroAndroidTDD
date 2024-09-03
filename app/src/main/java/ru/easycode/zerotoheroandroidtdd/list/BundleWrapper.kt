package ru.easycode.zerotoheroandroidtdd.list

import android.os.Bundle
import kotlin.collections.ArrayList

/**
 * @see ru.easycode.zerotoheroandroidtdd.core.SingleLiveEvent
 * */
interface BundleWrapper {

    interface Save : BundleWrapper {
        fun save(list: ArrayList<CharSequence>)
    }

    interface Restore : BundleWrapper {
        fun restore(): List<CharSequence>
    }

    interface Mutable : Save, Restore

    class Base(
        private val bundle: Bundle
    ) : Mutable {

        override fun save(list: ArrayList<CharSequence>) {
            bundle.putCharSequenceArrayList(KEY_LIST, list)
        }

        override fun restore(): List<CharSequence> {
            return bundle.getCharSequenceArrayList(KEY_LIST) ?: ArrayList()
        }
    }

    private companion object {
        const val KEY_LIST = "key_list"
    }
}