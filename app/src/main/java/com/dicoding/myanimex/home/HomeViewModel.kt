package com.dicoding.myanimex.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.core.domain.usecase.AnimeUseCase

class HomeViewModel(animeUseCase: AnimeUseCase) : ViewModel() {

    val anime = animeUseCase.getAllAnime().asLiveData()

}