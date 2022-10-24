package com.dicoding.core.data.source.local


import com.dicoding.core.data.source.local.room.AnimeDao
import kotlinx.coroutines.flow.Flow


class LocalDataSource(private val animeDao: AnimeDao) {

    fun getAllAnime(): Flow<List<com.dicoding.core.data.source.local.entity.AnimeEntity>> = animeDao.getAllAnime()

    fun getFavoriteAnime(): Flow<List<com.dicoding.core.data.source.local.entity.AnimeEntity>> = animeDao.getFavoriteAnime()

    suspend fun insertAnime(animeList: List<com.dicoding.core.data.source.local.entity.AnimeEntity>) = animeDao.insertAnime(animeList)

    fun setFavoriteAnime(anime: com.dicoding.core.data.source.local.entity.AnimeEntity, newState: Boolean) {
        anime.isFavorite = newState
        animeDao.updateFavoriteAnime(anime)
    }
}