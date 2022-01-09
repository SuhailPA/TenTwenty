package com.suhail.tentwenty.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.suhail.tentwenty.adapters.UpcomingMoviesAdapter
import com.suhail.tentwenty.databinding.FragmentHomeScreenBinding
import com.suhail.tentwenty.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreenFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    lateinit var binding: FragmentHomeScreenBinding
    lateinit var movieAdapter: UpcomingMoviesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeScreenBinding.inflate(layoutInflater)
        movieAdapter = UpcomingMoviesAdapter()
        binding.movieItems.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.movieResults.observe(viewLifecycleOwner, Observer {
            if (!it.data?.isEmpty()!!) Log.i("Results", it.data[0].originalTitle)
            movieAdapter.differ.submitList(it.data)

        })

        return binding.root
    }

}