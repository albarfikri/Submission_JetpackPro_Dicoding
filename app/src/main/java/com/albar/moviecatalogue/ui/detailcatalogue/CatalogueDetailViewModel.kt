package com.albar.moviecatalogue.ui.detailcatalogue

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.albar.moviecatalogue.data.local.entity.MoviesEntity
import com.albar.moviecatalogue.data.local.entity.TvShowsEntity
import com.albar.moviecatalogue.data.source.CatalogueRepository

class CatalogueDetailViewModel(private val repository: CatalogueRepository) : ViewModel() {

    fun getMovieDetailById(idMovies: Int): LiveData<MoviesEntity> =
        repository.getMovieById(idMovies)

    fun getTvShowDetailById(tvShowsId: Int): LiveData<TvShowsEntity> =
        repository.getTvShowById(tvShowsId)

    fun setFavMovie(movie: MoviesEntity) {
        repository.setFavMovie(movie)
    }

    fun setFavTvShow(tvShow: TvShowsEntity) {
        repository.setFavTvShow(tvShow)
    }
}