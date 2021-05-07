package com.albar.moviecatalogue.ui.detailcatalogue

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.albar.moviecatalogue.data.CatalogueDataModel
import com.albar.moviecatalogue.data.source.CatalogueRepository

class CatalogueDetailViewModel(private val repository: CatalogueRepository) : ViewModel() {

    fun getMovieDetailById(movieId: Int): LiveData<CatalogueDataModel> =
        repository.getMovieById(movieId)

    fun getTvShowDetailById(tvShowsId: Int): LiveData<CatalogueDataModel> =
        repository.getTvShowById(tvShowsId)

}