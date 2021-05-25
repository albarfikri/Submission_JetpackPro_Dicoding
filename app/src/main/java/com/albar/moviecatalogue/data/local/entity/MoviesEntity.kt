package com.albar.moviecatalogue.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movies")
@Parcelize
data class MoviesEntity(
    @PrimaryKey
    @ColumnInfo(name = "idMovies")
    var idMovies: Int = 0,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String? = null,

    @ColumnInfo(name = "voteAverage")
    var voteAverage: Double = 0.0,

    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @ColumnInfo(name = "posterPath")
    var posterPath: String? = null,

    @ColumnInfo(name = "backDropPath")
    var backDropPath: String? = null,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "isFavorited")
    var isFavorited: Boolean = false
) : Parcelable