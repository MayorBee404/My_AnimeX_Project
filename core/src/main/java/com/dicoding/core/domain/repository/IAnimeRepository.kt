package com.dicoding.core.domain.repository

import com.dicoding.core.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface IAnimeRepository {

    fun getAllAnime(): Flow<com.dicoding.core.data.Resource<List<Anime>>>

    fun getFavoriteAnime(): Flow<List<Anime>>

    fun setFavoriteAnime(anime: Anime, state: Boolean)

}