package ru.easycode.zerotoheroandroidtdd.delete

import ru.easycode.zerotoheroandroidtdd.core.LiveDataWrapper

interface ItemTextLiveDataWrapper {

    interface Read : LiveDataWrapper.Read<String>

    interface Update : LiveDataWrapper.Update<String>

    interface Mutable : Read, Update

    class Base : LiveDataWrapper.Abstract<String>(), Mutable

}