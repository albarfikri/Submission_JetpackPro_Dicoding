package com.albar.moviecatalogue.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.albar.moviecatalogue.data.local.entity.MoviesEntity
import com.albar.moviecatalogue.data.local.entity.TvShowsEntity
import com.albar.moviecatalogue.data.local.room.CatalogueDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val mCatalogueDao: CatalogueDao) {

    fun getAllMovies(): DataSource.Factory<Int, MoviesEntity> = mCatalogueDao.getAllMovies()

    fun getAllTvShows(): DataSource.Factory<Int, TvShowsEntity> = mCatalogueDao.getAllTvShows()

    fun getAllFavByMovies(): DataSource.Factory<Int, MoviesEntity> =
        mCatalogueDao.getAllFavByMovies()

    fun getAllFavByTvShows(): DataSource.Factory<Int, TvShowsEntity> =
        mCatalogueDao.getAllFavByTvShows()

    fun insertMovies(movies: List<MoviesEntity>) = mCatalogueDao.insertMovies(movies)

    fun insertTvShows(tvShows: List<TvShowsEntity>) = mCatalogueDao.insertTvShows(tvShows)

    fun getDetailMovie(idMovies: Int): LiveData<MoviesEntity> =
        mCatalogueDao.getDetailMovie(idMovies)

    fun getDetailTvShows(tvShowId: Int): LiveData<TvShowsEntity> =
        mCatalogueDao.getDetailTvShow(tvShowId)

    fun setFavMovie(movie: MoviesEntity) {
        movie.isFavorited = !movie.isFavorited
        mCatalogueDao.updateMovie(movie)
    }

    fun setFavTvShow(tvShow: TvShowsEntity) {
        tvShow.isFavorited = !tvShow.isFavorited
        mCatalogueDao.updateTvShow(tvShow)
    }
}