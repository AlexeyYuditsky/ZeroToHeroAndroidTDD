package ru.easycode.zerotoheroandroidtdd.core

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.easycode.zerotoheroandroidtdd.core.model.ItemCache

@Database(entities = [ItemCache::class], version = 1)
abstract class ItemsDataBase : RoomDatabase() {
    abstract fun itemsDao(): ItemsDao
}