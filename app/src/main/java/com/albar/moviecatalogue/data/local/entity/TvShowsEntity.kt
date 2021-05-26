package com.albar.moviecatalogue.data.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tvshows")
@Parcelize
data class TvShowsEntity(
    @PrimaryKey
    @ColumnInfo(name = "idTvShow")
    var idTvShow: Int = 0,

    @ColumnInfo(name = "firstAirDate")
    var firstAirDate: String? = null,

    @ColumnInfo(name = "voteAverage")
    var voteAverage: Double = 0.0,

    @ColumnInfo(name = "overiew")
    var overview: String? = null,

    @ColumnInfo(name = "posterPath")
    var posterPath: String? = null,

    @ColumnInfo(name = "backDropPath")
    var backDropPath: String? = null,

    @ColumnInfo(name = "originalName")
    var originalName: String? = null,

    @NonNull
    @ColumnInfo(name = "isFavorited")
    var isFavorited: Boolean = false
) : Parcelable