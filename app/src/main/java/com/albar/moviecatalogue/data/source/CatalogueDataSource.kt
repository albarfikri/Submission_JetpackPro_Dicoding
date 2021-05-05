package com.albar.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.albar.moviecatalogue.data.CatalogueDataModel

interface CatalogueDataSource {

    fun getMovie(): LiveData<List<CatalogueDataModel>>

    fun getMovieById(movieId: Int): LiveData<CatalogueDataModel>

    fun getTvShow(): LiveData<List<CatalogueDataModel>>

    fun getTvShowById(tvShowId: Int): LiveData<CatalogueDataModel>

}