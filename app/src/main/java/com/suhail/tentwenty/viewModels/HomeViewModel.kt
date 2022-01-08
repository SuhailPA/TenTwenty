package com.suhail.tentwenty.viewModels

import androidx.lifecycle.ViewModel
import com.suhail.tentwenty.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val repository: MovieRepository
    ) : ViewModel() {
}