package ru.easycode.zerotoheroandroidtdd.core

import android.content.Context
import androidx.room.Room

class Core(
    private val context: Context
) {

    private val database by lazy {
        Room.databaseBuilder(
            context = context,
            klass = ItemsDataBase::class.java,
            name = "items_database"
        ).build()
    }

    fun dao() = database.itemsDao()

}