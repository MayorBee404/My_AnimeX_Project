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
import com.dicoding.myanimex.MainActivity
import kotlinx.android.synthetic.main.fragment_favourite.*
import org.koin.core.context.loadKoinModules
import org.koin.androidx.viewmodel.ext.android.viewModel

import timber.log.Timber

class FavouriteFragment : Fragment() {

    private val viewModel: FavouriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favourite, container, false)
        loadKoinModules(favoriteModule)

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val animeAdapter = AnimeAdapter { item -> showDetail(item) }
            (activity as MainActivity).supportActionBar?.title = getString(com.dicoding.myanimex.R.string.menu_favorite)

            viewModel.favoriteAnime.observe(viewLifecycleOwner) { anime ->
                animeAdapter.setData(anime)
                view_empty.visibility = if (anime.isNotEmpty()) View.GONE else View.VISIBLE
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
        findNavController().navigate(
            FavouriteFragmentDirections.actionFavoriteFragmentToDetailAnimeActivity(anime)
            )
    }
}