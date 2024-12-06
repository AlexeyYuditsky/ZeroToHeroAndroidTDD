package ru.easycode.zerotoheroandroidtdd

import org.junit.Assert

class Order {

    private val list = mutableListOf<String>()

    fun add(value: String) {
        list.add(value)
    }

    fun checkCallsList(expected: List<String>) {
        Assert.assertEquals(expected, list)
    }
}