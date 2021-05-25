package com.albar.moviecatalogue.ui.main.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.albar.moviecatalogue.data.local.entity.MoviesEntity
import com.albar.moviecatalogue.data.local.entity.TvShowsEntity
import com.albar.moviecatalogue.data.source.CatalogueRepository
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemTvShow
import com.albar.moviecatalogue.vo.Resource
import javax.inject.Inject

class TvShowViewModel @Inject constructor(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getAllTvShowList(): LiveData<Resource<PagedList<TvShowsEntity>>> =
        catalogueRepository.getTvShows()
//    fun getLoadingState(): LiveData<Boolean> = catalogueRepository.isLoading
}