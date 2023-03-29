package com.dicoding.myanimex.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.core.data.Resource
import com.dicoding.myanimex.R
import com.dicoding.core.domain.model.Anime
import com.dicoding.core.ui.AnimeAdapter
import com.dicoding.myanimex.MainActivity
import com.dicoding.myanimex.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null

    // with the backing property of the kotlin we extract
    // the non null value of the _binding
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            if (activity != null) {
            val animeAdapter = AnimeAdapter { item -> showDetail(item) }
                (activity as MainActivity).supportActionBar?.title = getString(R.string.menu_home)

            homeViewModel.anime.observe(viewLifecycleOwner) { anime ->
                if (anime != null) {
                    when (anime) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            animeAdapter.setData(anime.data)
                            binding.progressBar.visibility = View.GONE
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.apply {
                                view.visibility = View.VISIBLE
                            }
                            binding.viewError.tvError.text = anime.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }

                with(binding.rvAnime) {
                layoutManager = GridLayoutManager(requireContext(), 2)
                setHasFixedSize(true)
                adapter = animeAdapter

            }
        }
    }

    private fun showDetail(anime: Anime) {
        Timber.d("OnClick : ${anime.canonicalTitle}")
        this.findNavController()
            .navigate(HomeFragmentDirections.actionHomeFragmentToDetailAnimeActivity(anime))
    }
}