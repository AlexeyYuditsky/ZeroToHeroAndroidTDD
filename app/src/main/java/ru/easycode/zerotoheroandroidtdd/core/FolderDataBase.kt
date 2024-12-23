package ru.easycode.zerotoheroandroidtdd.core

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FolderCache::class], version = 1)
abstract class FolderDataBase : RoomDatabase() {
    abstract fun folderDao(): FoldersDao
}