package ru.easycode.zerotoheroandroidtdd

import org.junit.Assert.assertEquals

class Order {

    private val callsList = mutableListOf<String>()

    fun add(value: String) {
        callsList.add(value)
    }

    fun checkCallsList(expected: List<String>) {
        assertEquals(expected, callsList)
    }

}