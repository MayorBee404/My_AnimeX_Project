package com.dicoding.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.core.data.source.local.entity.AnimeEntity

@Database(entities = [com.dicoding.core.data.source.local.entity.AnimeEntity::class], version = 1, exportSchema = false)
abstract class AnimeDatabase : RoomDatabase() {

    abstract fun animeDao(): AnimeDao

}