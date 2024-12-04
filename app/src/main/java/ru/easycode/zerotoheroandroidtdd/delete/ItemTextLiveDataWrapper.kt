package ru.easycode.zerotoheroandroidtdd.delete

import ru.easycode.zerotoheroandroidtdd.core.LiveDataWrapper

interface ItemTextLiveDataWrapper {

    interface Read : LiveDataWrapper.Read<String>

    interface Update : LiveDataWrapper.Update<String>

    interface Value {
        fun liveDataValue(): String
    }

    interface Mutable : Read, Update, Value

    class Base : LiveDataWrapper.Abstract<String>(), Mutable {

        override fun liveDataValue(): String = liveData.value!!

    }

}