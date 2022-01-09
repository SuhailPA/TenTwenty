package com.suhail.tentwenty.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.suhail.tentwenty.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: MovieRepository
    ) : ViewModel() {
    var movieResults = repository.readAllUpcomingMovies().asLiveData()


}