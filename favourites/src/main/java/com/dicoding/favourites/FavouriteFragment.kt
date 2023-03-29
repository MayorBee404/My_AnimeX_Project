package com.dicoding.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.core.domain.model.Anime
import com.dicoding.core.ui.AnimeAdapter
import com.dicoding.favourites.databinding.FragmentFavouriteBinding
import com.dicoding.myanimex.MainActivity
import org.koin.core.context.loadKoinModules
import org.koin.androidx.viewmodel.ext.android.viewModel

import timber.log.Timber

class FavouriteFragment : Fragment() {

    private val viewModel: FavouriteViewModel by viewModel()
    private var _binding: FragmentFavouriteBinding? = null

    // with the backing property of the kotlin we extract
    // the non null value of the _binding
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        loadKoinModules(favoriteModule)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val animeAdapter = AnimeAdapter { item -> showDetail(item) }
            (activity as MainActivity).supportActionBar?.title = getString(com.dicoding.myanimex.R.string.menu_favorite)

            viewModel.favoriteAnime.observe(viewLifecycleOwner) { anime ->
                animeAdapter.setData(anime)
                binding.viewEmpty.apply {
                    view.visibility = if (anime.isNotEmpty()) View.GONE else View.VISIBLE
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
        findNavController().navigate(
            FavouriteFragmentDirections.actionFavoriteFragmentToDetailAnimeActivity(anime)
            )
    }
}