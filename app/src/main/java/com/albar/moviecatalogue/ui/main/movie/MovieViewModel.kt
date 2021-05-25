package com.albar.moviecatalogue.ui.main.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.albar.moviecatalogue.data.local.entity.MoviesEntity
import com.albar.moviecatalogue.data.source.CatalogueRepository
import com.albar.moviecatalogue.vo.Resource
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val catalogueRepository: CatalogueRepository) :
    ViewModel() {
    fun getAllMoviesList(): LiveData<Resource<PagedList<MoviesEntity>>> =
        catalogueRepository.getMovies()
}