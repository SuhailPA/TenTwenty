package com.suhail.tentwenty.api

import com.suhail.tentwenty.data.UpcomingMovies
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {
    companion object {
        const val API_KEY = "f6d8693db12e1b1399d7d2212be79767"
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
    }

    @GET("upcoming?")
    suspend fun getAllMoviesFromAPI(
        @Query("api_key") apiKey: String = API_KEY
    ): UpcomingMovies

}