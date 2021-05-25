package com.albar.moviecatalogue.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.albar.moviecatalogue.data.local.entity.MoviesEntity
import com.albar.moviecatalogue.data.local.entity.TvShowsEntity
import com.albar.moviecatalogue.data.source.CatalogueRepository
import javax.inject.Inject

class FavViewModel @Inject constructor(private val catalogueRepository: CatalogueRepository) :
    ViewModel() {
    fun getFavMovies(): LiveData<PagedList<MoviesEntity>> = catalogueRepository.getAllFavByMovies()
    fun getFavTvShow(): LiveData<PagedList<TvShowsEntity>> =
        catalogueRepository.getAllFavByTvShows()
}