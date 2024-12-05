package ru.easycode.zerotoheroandroidtdd.repository

import ru.easycode.zerotoheroandroidtdd.core.Now

interface FakeNow : Now {

    class Base(
        private var value: Long
    ) : FakeNow {

        override fun nowMillis(): Long {
            return ++value
        }

    }

}