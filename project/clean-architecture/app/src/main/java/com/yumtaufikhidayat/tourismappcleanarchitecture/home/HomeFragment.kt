package com.yumtaufikhidayat.tourismappcleanarchitecture.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yumtaufikhidayat.tourismappcleanarchitecture.R
import com.yumtaufikhidayat.tourismappcleanarchitecture.core.data.Resource
import com.yumtaufikhidayat.tourismappcleanarchitecture.core.ui.TourismAdapter
import com.yumtaufikhidayat.tourismappcleanarchitecture.core.ui.ViewModelFactory
import com.yumtaufikhidayat.tourismappcleanarchitecture.core.utils.navigateToDetail
import com.yumtaufikhidayat.tourismappcleanarchitecture.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var homeViewModel: HomeViewModel? = null
    private val tourismAdapter by lazy { TourismAdapter { navigateToDetail(it) } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            setHomeAdapter()
            setViewModel()
            setData()
        }
    }

    private fun setHomeAdapter() {
        binding.rvTourism.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tourismAdapter
        }
    }

    private fun setViewModel() {
        val factory = ViewModelFactory.getInstance(requireActivity())
        homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
    }

    private fun setData() {
        homeViewModel?.tourism?.observe(viewLifecycleOwner) {
            binding.apply {
                if (it != null) {
                    when (it) {
                        is Resource.Loading -> showProgressBar(true)
                        is Resource.Success -> {
                            showProgressBar(false)
                            tourismAdapter.submitList(it.data)
                        }
                        is Resource.Error -> {
                            showProgressBar(false)
                            viewError.root.isVisible = true
                            viewError.tvError.text = it.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }
        }
    }

    private fun showProgressBar(isShow: Boolean) {
        binding.progressBar.isVisible = isShow
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}