package com.albar.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.albar.moviecatalogue.data.local.entity.MoviesEntity
import com.albar.moviecatalogue.data.local.entity.TvShowsEntity
import com.albar.moviecatalogue.vo.Resource

interface CatalogueDataSource {

    fun getMovies(): LiveData<Resource<PagedList<MoviesEntity>>>

    fun getTvShows(): LiveData<Resource<PagedList<TvShowsEntity>>>

    fun getMovieById(idMovies: Int): LiveData<MoviesEntity>

    fun getTvShowById(tvShowId: Int): LiveData<TvShowsEntity>

    fun getAllFavByMovies(): LiveData<PagedList<MoviesEntity>>

    fun getAllFavByTvShows(): LiveData<PagedList<TvShowsEntity>>

    fun setFavMovie(movie: MoviesEntity)

    fun setFavTvShow(tvShow: TvShowsEntity)
}