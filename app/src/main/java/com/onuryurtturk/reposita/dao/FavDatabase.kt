package com.onuryurtturk.reposita.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [FavEntity::class],
    version = 1
)
abstract class FavDatabase : RoomDatabase() {

    abstract fun favDao():FavDao

    companion object {
        @Volatile private var instance: FavDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context) = instance ?: synchronized(LOCK){
            instance?:createDB(context).also{ instance = it}
        }
        private fun createDB(context: Context) = Room.databaseBuilder(context,FavDatabase::class.java, "favs.db").allowMainThreadQueries().build()
    }

}