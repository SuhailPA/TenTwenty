package com.suhail.tentwenty.repository

import android.util.Log
import androidx.room.withTransaction
import com.suhail.tentwenty.api.MoviesAPI
import com.suhail.tentwenty.room.MovieDatabase
import com.suhail.tentwenty.util.networkBoundResource
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val db: MovieDatabase,
    private val api: MoviesAPI
) {
    private val moviesDao = db.movieDao()
    fun readAllUpcomingMovies() = networkBoundResource(
        query = {
            moviesDao.getAllMovies()
        },
        fetch = {
            api.getAllMoviesFromAPI()
        },
        saveFetchResult = {
            Log.i("movieResults", it.results[0].originalTitle)
            db.withTransaction {
                moviesDao.deleteAllMovies()
                moviesDao.insertAllMovies(it.results)
            }
        },
        shouldFetch = {
            return@networkBoundResource true
        }
    )
}