package ru.easycode.zerotoheroandroidtdd.main

import ru.easycode.zerotoheroandroidtdd.list.BundleWrapper

interface FakeBundleWrapper : BundleWrapper.Mutable {

    class Base : FakeBundleWrapper {

        private val cached = ArrayList<CharSequence>()

        override fun restore(): List<CharSequence> = cached

        override fun save(list: ArrayList<CharSequence>) {
            cached.addAll(list)
        }

    }

}