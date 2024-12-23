package ru.easycode.zerotoheroandroidtdd.core

import android.content.Context
import androidx.room.Room

class Core(
    private val context: Context
) {

    private val database by lazy {
        Room.databaseBuilder(
            context = context,
            klass = FolderDataBase::class.java,
            name = "folder_database"
        ).build()
    }

    fun folderDao() = database.folderDao()

}