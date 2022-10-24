package com.dicoding.myanimex.detail

import androidx.lifecycle.ViewModel
import com.dicoding.core.domain.model.Anime
import com.dicoding.core.domain.usecase.AnimeUseCase


class DetailAnimeViewModel(private val animeUseCase: AnimeUseCase) : ViewModel() {

    fun setFavoriteAnime(anime: Anime, newStatus: Boolean) =
        animeUseCase.setFavoriteAnime(anime, newStatus)

}