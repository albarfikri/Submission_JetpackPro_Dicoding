package com.albar.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.albar.moviecatalogue.data.CatalogueDataModel
import com.albar.moviecatalogue.data.CatalogueEntity
import com.albar.moviecatalogue.data.source.CatalogueRepository
import com.albar.moviecatalogue.utils.DataDummy

class TvShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getAllTvShowDummy(): List<CatalogueEntity> = DataDummy.getAllDummyTvShow()
    fun getAllTvShowsList(): LiveData<List<CatalogueDataModel>> = catalogueRepository.getTvShow()
}