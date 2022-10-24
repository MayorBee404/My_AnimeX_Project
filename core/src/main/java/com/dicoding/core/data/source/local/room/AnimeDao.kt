package com.dicoding.core.data.source.local.room

import androidx.room.*
import com.dicoding.core.data.source.local.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface AnimeDao {

    @Query("SELECT * FROM anime")
    fun getAllAnime(): Flow<List<com.dicoding.core.data.source.local.entity.AnimeEntity>>

    @Query("SELECT * FROM anime where isFavorite = 1")
    fun getFavoriteAnime(): Flow<List<com.dicoding.core.data.source.local.entity.AnimeEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAnime(anime: List<com.dicoding.core.data.source.local.entity.AnimeEntity>)

    @Update
    fun updateFavoriteAnime(anime: com.dicoding.core.data.source.local.entity.AnimeEntity)

}