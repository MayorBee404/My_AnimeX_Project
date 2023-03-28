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
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_error.*
import timber.log.Timber


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            if (activity != null) {
            val animeAdapter = AnimeAdapter { item -> showDetail(item) }
                (activity as MainActivity).supportActionBar?.title = getString(R.string.menu_home)

            homeViewModel.anime.observe(viewLifecycleOwner) { anime ->
                if (anime != null) {
                    when (anime) {
                        is Resource.Loading -> progress_bar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            progress_bar.visibility = View.GONE
                            animeAdapter.setData(anime.data)
                        }
                        is Resource.Error -> {
                            progress_bar.visibility = View.GONE
                            view_error.visibility = View.VISIBLE
                            tv_error.text = anime.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }

                with(rv_anime) {
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