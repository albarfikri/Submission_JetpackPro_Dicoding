package com.albar.moviecatalogue.ui.detailcatalogue

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.albar.moviecatalogue.data.source.CatalogueRepository
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemMovie
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemTvShow

class CatalogueDetailViewModel(private val repository: CatalogueRepository) : ViewModel() {

    fun getMovieDetailById(movieId: Int): LiveData<ResultsItemMovie> =
        repository.getMovieById(movieId)

    fun getTvShowDetailById(tvShowsId: Int): LiveData<ResultsItemTvShow> =
        repository.getTvShowById(tvShowsId)

    fun getLoadingState(): LiveData<Boolean> = repository.isLoading
}