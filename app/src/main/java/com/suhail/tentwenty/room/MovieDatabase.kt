package com.suhail.tentwenty.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.suhail.tentwenty.data.Result

@Database(entities = [Result::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao() : MovieDao
}