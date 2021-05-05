package com.albar.moviecatalogue.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class CatalogueDataModel(
    val id: Int? = 0,
    val title: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val releaseDate: String? = null,
    val popularity: Double? = 0.0,
    val voteAverage: Double? = 0.0,
    val voteCount: Int? = 0,
    val overview: String? = null,
) : Parcelable