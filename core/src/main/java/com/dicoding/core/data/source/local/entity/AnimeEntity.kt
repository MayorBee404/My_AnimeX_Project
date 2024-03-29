package com.dicoding.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "anime")
data class AnimeEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title")
    var canonicalTitle: String,

    @ColumnInfo(name = "startDate")
    var startDate: String,

    @ColumnInfo(name = "rating")
    var averageRating: Double,

    @ColumnInfo(name = "synopsis")
    var synopsis: String,

    @ColumnInfo(name = "posterImage")
    var posterImage: String,

    @ColumnInfo(name = "coverImage")
    var coverImage: String,

    @ColumnInfo(name = "youtubeVideoId")
    var youtubeVideoId: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable