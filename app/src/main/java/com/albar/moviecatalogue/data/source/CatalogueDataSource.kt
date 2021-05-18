package com.albar.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.albar.moviecatalogue.data.local.entity.MoviesEntity
import com.albar.moviecatalogue.data.local.entity.TvShowsEntity
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemMovie
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemTvShow
import com.bumptech.glide.load.engine.Resource

interface CatalogueDataSource {

    fun getMovie(): LiveData<Resource<PagedList<ResultsItemMovie>>>

    fun getMovieById(movieId: Int): LiveData<ResultsItemMovie>

    fun getAllFavByMovies(): LiveData<PagedList<MoviesEntity>>

    fun setFavMovie(movie: MoviesEntity)

    fun setFavTvShow(tvShow: TvShowsEntity)

    fun getTvShow(): LiveData<Resource<PagedList<ResultsItemTvShow>>>

    fun getTvShowById(tvShowId: Int): LiveData<ResultsItemTvShow>

    fun getAllFavByTvShows(): LiveData<PagedList<TvShowsEntity>>

}