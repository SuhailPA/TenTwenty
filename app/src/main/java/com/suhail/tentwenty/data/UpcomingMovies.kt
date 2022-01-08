package com.suhail.tentwenty.data


import com.google.gson.annotations.SerializedName

data class UpcomingMovies(

    @SerializedName("results")
    val results: List<Result>,

)