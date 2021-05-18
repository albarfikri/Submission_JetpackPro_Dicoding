package com.albar.moviecatalogue.data.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.albar.moviecatalogue.data.local.entity.MoviesEntity
import com.albar.moviecatalogue.data.local.entity.TvShowsEntity

@Dao
interface CatalogueDao {
    // Getting Data
    @Query("SELECT * FROM movies")
    fun getAllMovies(): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM tvshows")
    fun getAllTvShows(): DataSource.Factory<Int, TvShowsEntity>

    // Getting Data by Favorite
    @Query("SELECT * FROM movies WHERE idMovies = :idMovies")
    fun getDetailMovie(idMovies: Int): LiveData<MoviesEntity>

    @Query("SELECT * FROM tvshows WHERE idTvShow = :idTvShow")
    fun getDetailTvShow(idTvShow: Int): LiveData<TvShowsEntity>

    @Query("SELECT * FROM movies WHERE isFavorited = 1")
    fun getAllFavByMovies(): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM tvshows WHERE isFavorited = 1")
    fun getAllFavByTvShows(): DataSource.Factory<Int, TvShowsEntity>

    // Inserting Data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MoviesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShowsEntity>)

    // Update
    @Update(entity = MoviesEntity::class)
    fun updateMovie(movie: MoviesEntity)

    @Update(entity = TvShowsEntity::class)
    fun updateTvShow(tvShow: TvShowsEntity)

}