package ru.easycode.zerotoheroandroidtdd.core.other

import android.util.Log

fun <T>log(message:T) {
    Log.d("MyLog", message.toString())
}