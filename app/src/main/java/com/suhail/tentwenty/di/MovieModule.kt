package com.suhail.tentwenty.di

import android.app.Application
import androidx.room.Room
import com.suhail.tentwenty.api.MoviesAPI
import com.suhail.tentwenty.api.MoviesAPI.Companion.BASE_URL
import com.suhail.tentwenty.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieModule {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideMovieAPI(retrofit: Retrofit) : MoviesAPI = retrofit.create(MoviesAPI::class.java)

    @Provides
    @Singleton
    fun provideRoomDB(app:Application) : MovieDatabase = Room.databaseBuilder(app,
        MovieDatabase::class.java,
        "movie_db"
    ).build()
}