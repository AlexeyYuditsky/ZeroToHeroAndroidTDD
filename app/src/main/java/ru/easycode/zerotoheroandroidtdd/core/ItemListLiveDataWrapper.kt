package ru.easycode.zerotoheroandroidtdd.core

import ru.easycode.zerotoheroandroidtdd.core.model.ItemUi

interface ItemListLiveDataWrapper {

    interface Read : LiveDataWrapper.Read<List<ItemUi>>

    interface Update : LiveDataWrapper.Update<List<ItemUi>>

    interface UpdateItem {
        fun update(value: ItemUi)
    }

    interface Add {
        fun add(value: ItemUi)
    }

    interface Delete {
        fun delete(value: ItemUi)
    }

    interface Mutable : Update, Read, Delete, UpdateItem

    interface All : Mutable, Add

    class Base : LiveDataWrapper.Abstract<List<ItemUi>>(), All {

        override fun add(value: ItemUi) {
            val currentList = liveData.value?.plus(value) ?: listOf(value)
            update(currentList)
        }

        override fun delete(value: ItemUi) {
            val list = liveData.value?.toMutableList() ?: return
            list.remove(value)
            update(list)
        }

        override fun update(value: ItemUi) {
            val itemUiList = liveData.value?.toMutableList() ?: return
            itemUiList.find { it.areItemsSame(value) }?.let {
                itemUiList[itemUiList.indexOf(it)] = value
                update(itemUiList)
            }
        }

    }

}