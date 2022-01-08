package com.suhail.tentwenty.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.suhail.tentwenty.data.UpcomingMovies
import retrofit2.http.DELETE


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(movies:UpcomingMovies)

    @Query("SELECT * FROM movieResult")
    fun getAllMovies():UpcomingMovies

    @Query("DELETE FROM movieResult")
    fun deleteAllMovies()

}