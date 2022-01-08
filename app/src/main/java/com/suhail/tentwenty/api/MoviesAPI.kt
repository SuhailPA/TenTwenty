package com.suhail.tentwenty.api

import com.suhail.tentwenty.data.UpcomingMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {
    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
    }

    @GET("upcoming?")
    suspend fun getAllMoviesFromAPI(
        @Query("api_key")apiKey:String
    ):Response<UpcomingMovies>
}