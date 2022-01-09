package com.suhail.tentwenty.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.suhail.tentwenty.data.Result
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(movies: List<Result>)

    @Query("SELECT * FROM movieResult")
    fun getAllMovies(): Flow<List<Result>>

    @Query("DELETE FROM movieResult")
    suspend fun deleteAllMovies()

}