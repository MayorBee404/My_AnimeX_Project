package com.dicoding.core.domain.usecase

import com.dicoding.core.domain.model.Anime
import com.dicoding.core.domain.repository.IAnimeRepository
import kotlinx.coroutines.flow.Flow

class AnimeInteractor(private val animeRepository: IAnimeRepository): AnimeUseCase {
    override fun getAllAnime(): Flow<com.dicoding.core.data.Resource<List<Anime>>> {
        return animeRepository.getAllAnime()
    }

    override fun getFavoriteAnime(): Flow<List<Anime>> {
        return animeRepository.getFavoriteAnime()
    }

    override fun setFavoriteAnime(anime: Anime, state: Boolean) {
        return animeRepository.setFavoriteAnime(anime, state)
    }
}