package ru.easycode.zerotoheroandroidtdd.core

import ru.easycode.zerotoheroandroidtdd.core.model.ItemUi

interface ItemListLiveDataWrapper {

    interface Read : LiveDataWrapper.Read<List<ItemUi>>

    interface Update : LiveDataWrapper.Update<List<ItemUi>>

    interface Add {
        fun add(item: ItemUi)
    }

    interface Delete {
        fun delete(item: ItemUi)
    }

    interface Mutable : Update, Read, Delete

    interface All : Mutable, Add

    class Base : LiveDataWrapper.Abstract<List<ItemUi>>(), All {

        override fun add(item: ItemUi) {
            val currentList = liveData.value?.plus(item) ?: listOf(item)
            update(currentList)
        }

        override fun delete(item: ItemUi) {
            val list = liveData.value?.toMutableList() ?: return
            list.remove(item)
            update(list)
        }

    }

}