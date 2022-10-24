package com.dicoding.core.domain.usecase

import com.dicoding.core.data.Resource
import com.dicoding.core.domain.model.Anime

import kotlinx.coroutines.flow.Flow

interface AnimeUseCase {
    fun getAllAnime(): Flow<Resource<List<Anime>>>
    fun getFavoriteAnime(): Flow<List<Anime>>
    fun setFavoriteAnime(anime: Anime, state: Boolean)
}