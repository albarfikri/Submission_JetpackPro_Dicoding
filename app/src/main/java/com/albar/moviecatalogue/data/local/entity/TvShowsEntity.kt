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
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idTvShow")
    var id: Int? = null,

    @NonNull
    @ColumnInfo(name = "tvShowImage")
    var tvShowImage: String? = null,

    @NonNull
    @ColumnInfo(name = "tvShowName")
    var tvShowName: String? = null,

    @NonNull
    @ColumnInfo(name = "tvShowReview")
    var tvShowReview: String? = null,

    @NonNull
    @ColumnInfo(name = "tvShowImageView")
    var tvShowImageView: Int? = null,

    @NonNull
    @ColumnInfo(name = "tvShowDate")
    var tvShowDate: String? = null,

    @NonNull
    @ColumnInfo(name = "tvShowDuration")
    var tvShowDuration: String? = null,

    @NonNull
    @ColumnInfo(name = "isFavorited")
    var isFavorited: Boolean = false
) : Parcelable