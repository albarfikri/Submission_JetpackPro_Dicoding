package com.albar.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.albar.moviecatalogue.data.source.CatalogueRepository
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemTvShow

class TvShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getAllTvShowsList(): LiveData<List<ResultsItemTvShow>> = catalogueRepository.getTvShow()
    fun getLoadingState(): LiveData<Boolean> = catalogueRepository.isLoading
}