package com.suhail.tentwenty.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.suhail.tentwenty.R
import com.suhail.tentwenty.databinding.FragmentDetailedBinding
import com.suhail.tentwenty.viewModels.DetailedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailedFragment : Fragment() {
    private val viewModel: DetailedViewModel by viewModels()
    lateinit var binding: FragmentDetailedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailedBinding.inflate(layoutInflater)

        viewModel.posterImage.observe(viewLifecycleOwner, {

            Glide.with(requireContext())
                .load(it)
                .placeholder(R.drawable.media_library)
                .centerCrop()
                .into(binding.imagePosterDetail)
        })

        viewModel.posterTitle.observe(viewLifecycleOwner, {
            binding.movieNameDetail.text = it
        })

        viewModel.posterOverView.observe(viewLifecycleOwner, {
            binding.movieOverviewDetail.text = it
        })
        return binding.root
    }

}