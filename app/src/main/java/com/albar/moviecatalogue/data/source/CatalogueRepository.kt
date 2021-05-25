package com.albar.moviecatalogue.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.albar.moviecatalogue.data.NetworkBoundResource
import com.albar.moviecatalogue.data.local.LocalDataSource
import com.albar.moviecatalogue.data.local.entity.MoviesEntity
import com.albar.moviecatalogue.data.local.entity.TvShowsEntity
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemMovie
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemTvShow
import com.albar.moviecatalogue.data.source.remote.response.vo.ApiResponse
import com.albar.moviecatalogue.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatalogueRepository @Inject constructor(
    private val catalogueRemoteDataSource: CatalogueRemoteDataSource,
    private val catalogueLocalDataSource: LocalDataSource
) : CatalogueDataSource {

    override fun getMovies(): LiveData<Resource<PagedList<MoviesEntity>>> {
        return object : NetworkBoundResource<PagedList<MoviesEntity>, List<ResultsItemMovie>>() {
            override fun loadFromDB(): LiveData<PagedList<MoviesEntity>> {
                val configMovies = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                Log.d("Action :", "Call loadFromDB")
                return LivePagedListBuilder(
                    catalogueLocalDataSource.getAllMovies(),
                    configMovies
                ).build()
            }

            override fun shouldFetch(data: PagedList<MoviesEntity>?): Boolean {
                Log.d("Action :", "shouldFetch : " + data.toString())
                return data == null || data.isEmpty()
            }


            override fun createCall(): LiveData<ApiResponse<List<ResultsItemMovie>>> {
                Log.d("Action :", "createCall")
                return catalogueRemoteDataSource.getMovies()
            }


            override fun saveCallResult(data: List<ResultsItemMovie>) {
                val movies = ArrayList<MoviesEntity>()
                for (itemMovie in data) {
                    val movie = MoviesEntity(
                        itemMovie.id,
                        itemMovie.releaseDate,
                        itemMovie.voteAverage,
                        itemMovie.overview,
                        itemMovie.posterPath,
                        itemMovie.backdropPath,
                        itemMovie.title,
                        false
                    )
                    movies.add(movie)
                }
                Log.d("Testing", "From saveCallResult : " + movies.toString() + "\n")
                Log.d("Action :", "Call saveCallResult")
                catalogueLocalDataSource.insertMovies(movies)
            }
        }.asLiveData()
    }

    override fun getTvShows(): LiveData<Resource<PagedList<TvShowsEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowsEntity>, List<ResultsItemTvShow>>() {
            override fun loadFromDB(): LiveData<PagedList<TvShowsEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                Log.d("Action :", "Call loadFromDB")
                return LivePagedListBuilder(
                    catalogueLocalDataSource.getAllTvShows(),
                    config
                ).build()
            }

            override fun shouldFetch(data: PagedList<TvShowsEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsItemTvShow>>> =
                catalogueRemoteDataSource.getTvShows()

            override fun saveCallResult(data: List<ResultsItemTvShow>) {
                val tvShows = ArrayList<TvShowsEntity>()
                for (itemTvShow in data) {
                    val tvShow = TvShowsEntity(
                        itemTvShow.id,
                        itemTvShow.firstAirDate,
                        itemTvShow.voteAverage,
                        itemTvShow.overview,
                        itemTvShow.posterPath,
                        itemTvShow.backdropPath,
                        itemTvShow.originalName,
                        false
                    )
                    tvShows.add(tvShow)
                }
                Log.d("Output", tvShows.toString())
                catalogueLocalDataSource.insertTvShows(tvShows)
            }


        }.asLiveData()
    }

    override fun getMovieById(idMovies: Int): LiveData<MoviesEntity> =
        catalogueLocalDataSource.getDetailMovie(idMovies)

    override fun getTvShowById(tvShowId: Int): LiveData<TvShowsEntity> =
        catalogueLocalDataSource.getDetailTvShows(tvShowId)

    override fun getAllFavByMovies(): LiveData<PagedList<MoviesEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(catalogueLocalDataSource.getAllFavByMovies(), config).build()
    }

    override fun getAllFavByTvShows(): LiveData<PagedList<TvShowsEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(catalogueLocalDataSource.getAllFavByTvShows(), config).build()
    }

    override fun setFavMovie(movie: MoviesEntity) {
        CoroutineScope(IO).launch {
            catalogueLocalDataSource.setFavMovie(movie)
        }
    }

    override fun setFavTvShow(tvShow: TvShowsEntity) {
        CoroutineScope(IO).launch {
            catalogueLocalDataSource.setFavTvShow(tvShow)
        }
    }
}

