package com.suhail.tentwenty.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
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
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeScreenBinding.inflate(layoutInflater)
        movieAdapter = UpcomingMoviesAdapter()
        navController = findNavController()
        binding.movieItems.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.movieResults.observe(viewLifecycleOwner, Observer {
            if (!it.data?.isEmpty()!!)
                movieAdapter.differ.submitList(it.data)

        })

        movieAdapter.setOnClickListener {
            val action = HomeScreenFragmentDirections.actionHomeScreenFragmentToDetailedFragment(it)
            navController.navigate(action)
        }
        return binding.root
    }

}