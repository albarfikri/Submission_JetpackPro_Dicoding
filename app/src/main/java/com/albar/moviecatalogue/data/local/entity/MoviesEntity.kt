package com.albar.moviecatalogue.data.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movies")
@Parcelize
data class MoviesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idMovies")
    var idMovies: Int? = null,

    @NonNull
    @ColumnInfo(name = "tvMovieImage")
    var tvMovieImage: String? = null,

    @NonNull
    @ColumnInfo(name = "tvMovieName")
    var tvMovieName: String? = null,

    @NonNull
    @ColumnInfo(name = "tvMovieReview")
    var tvMovieReview: String? = null,

    @NonNull
    @ColumnInfo(name = "tvMovieRating")
    var tvMovieRating: Float? = null,

    @NonNull
    @ColumnInfo(name = "tvMovieDate")
    var tvMovieDate: String? = null,

    @NonNull
    @ColumnInfo(name = "isFavorited")
    var isFavorited: Boolean = false
) : Parcelable