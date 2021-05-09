package com.albar.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.albar.moviecatalogue.data.source.CatalogueRepository
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemMovie

class MovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getAllMoviesList(): LiveData<List<ResultsItemMovie>> = catalogueRepository.getMovie()
    fun getLoadingState(): LiveData<Boolean> = catalogueRepository.isLoading
}