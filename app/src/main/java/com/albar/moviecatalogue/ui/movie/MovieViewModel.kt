package com.albar.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.albar.moviecatalogue.data.CatalogueDataModel
import com.albar.moviecatalogue.data.CatalogueEntity
import com.albar.moviecatalogue.data.source.CatalogueRepository
import com.albar.moviecatalogue.utils.DataDummy

class MovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getAllMovieDummy(): List<CatalogueEntity> = DataDummy.getAllDummyMovie()
    fun getAllMoviesList(): LiveData<List<CatalogueDataModel>> = catalogueRepository.getMovie()
    fun getLoadingState() :LiveData<Boolean> = catalogueRepository.isLoading
}