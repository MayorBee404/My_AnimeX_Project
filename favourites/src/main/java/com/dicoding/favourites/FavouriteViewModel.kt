package com.dicoding.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.core.domain.usecase.AnimeUseCase

class FavouriteViewModel(animeUseCase: AnimeUseCase)  : ViewModel() {
    val favoriteAnime = animeUseCase.getFavoriteAnime().asLiveData()
}