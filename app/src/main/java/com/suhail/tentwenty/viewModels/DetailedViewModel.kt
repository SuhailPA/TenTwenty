package com.suhail.tentwenty.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.suhail.tentwenty.data.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailedViewModel
@Inject constructor(
    private val state: SavedStateHandle
) : ViewModel() {
    val posterImage = MutableLiveData<String>()
    val posterTitle = MutableLiveData<String>()
    val posterOverView = MutableLiveData<String>()

    init {
        initializeItems()
    }

    private fun initializeItems() {
        val item = state.get<Result>("movieDetails")
        posterImage.value = "https://image.tmdb.org/t/p/w500${item?.posterPath}"
        posterTitle.value = item?.title
        posterOverView.value = item?.overview
    }

}