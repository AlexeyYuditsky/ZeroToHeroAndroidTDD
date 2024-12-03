package ru.easycode.zerotoheroandroidtdd.core.navigation

import ru.easycode.zerotoheroandroidtdd.core.LiveDataWrapper

interface Navigation {

    interface Read : LiveDataWrapper.Read<Screen>

    interface Update : LiveDataWrapper.Update<Screen>

    interface Mutable : Read, Update

    class Base : LiveDataWrapper.Abstract<Screen>(), Mutable

}