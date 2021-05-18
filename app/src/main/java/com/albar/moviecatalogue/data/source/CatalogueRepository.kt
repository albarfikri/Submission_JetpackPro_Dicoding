package com.albar.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.albar.moviecatalogue.data.local.LocalDataSource
import com.albar.moviecatalogue.data.local.entity.MoviesEntity
import com.albar.moviecatalogue.data.local.entity.TvShowsEntity
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemMovie
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemTvShow
import com.albar.moviecatalogue.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatalogueRepository @Inject private constructor(
    private val catalogueRemoteDataSource: CatalogueRemoteDataSource,
    private val catalogueLocalDataSource: LocalDataSource
) : CatalogueDataSource {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        @Volatile
        private var instance: CatalogueRepository? = null


        fun getInstance(catalogueRemoteData: CatalogueRemoteDataSource): CatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogueRepository(catalogueRemoteData).apply { instance = this }
            }

    }

    override fun getMovie(): LiveData<Resource<PagedList<MoviesEntity>>> {
        val listMoviesOutput = MutableLiveData<List<ResultsItemMovie>>()
        _isLoading.value = true
        CoroutineScope(IO).launch {
            catalogueRemoteDataSource.getMovies(object :
                CatalogueRemoteDataSource.LoadMovieCallback {
                override fun onAllMovieReceived(movieCatalogueResponse: List<ResultsItemMovie>) {
                    val listMovies = ArrayList<ResultsItemMovie>()
                    for (response in movieCatalogueResponse) {
                        val movie = ResultsItemMovie(
                            response.id,
                            response.title,
                            response.posterPath,
                            response.backdropPath,
                            response.releaseDate,
                            response.popularity,
                            response.voteAverage,
                            response.voteCount,
                            response.overview
                        )
                        listMovies.add(movie)
                    }
                    listMoviesOutput.postValue(listMovies)
                    _isLoading.postValue(false)
                }
            })
        }
        return listMoviesOutput
    }

    override fun getTvShow(): LiveData<List<ResultsItemTvShow>> {
        val listTvShowOutput = MutableLiveData<List<ResultsItemTvShow>>()
        CoroutineScope(IO).launch {
            catalogueRemoteDataSource.getTvShows(object :
                CatalogueRemoteDataSource.LoadTvShowCallback {
                override fun onAllTvShowReceived(tvShowCatalogueResponse: List<ResultsItemTvShow>) {
                    val listTvShow = ArrayList<ResultsItemTvShow>()
                    for (response in tvShowCatalogueResponse) {
                        val tvShows = ResultsItemTvShow(
                            response.id,
                            response.originalName,
                            response.posterPath,
                            response.backdropPath,
                            response.firstAirDate,
                            response.popularity,
                            response.voteAverage,
                            response.voteCount,
                            response.overview
                        )
                        listTvShow.add(tvShows)
                        _isLoading.postValue(false)
                    }
                    listTvShowOutput.postValue(listTvShow)
                }
            })
        }
        return listTvShowOutput
    }

    override fun getTvShowById(tvShowId: Int): LiveData<ResultsItemTvShow> {
        val tvShowDetailOutput = MutableLiveData<ResultsItemTvShow>()
        CoroutineScope(IO).launch {
            catalogueRemoteDataSource.getTvShowDetail(
                tvShowId,
                object : CatalogueRemoteDataSource.LoadTvShowsByIdCallback {
                    override fun onTvShowsDetailReceived(tvShowsCatalogue: ResultsItemTvShow) {
                        val tvShowsDetail = ResultsItemTvShow(
                            tvShowsCatalogue.id,
                            tvShowsCatalogue.originalName,
                            tvShowsCatalogue.posterPath,
                            tvShowsCatalogue.backdropPath,
                            tvShowsCatalogue.firstAirDate,
                            tvShowsCatalogue.popularity,
                            tvShowsCatalogue.voteAverage,
                            tvShowsCatalogue.voteCount,
                            tvShowsCatalogue.overview
                        )
                        tvShowDetailOutput.postValue(tvShowsDetail)
                        _isLoading.postValue(false)
                    }
                })
        }
        return tvShowDetailOutput
    }


    override fun getMovieById(movieId: Int): LiveData<ResultsItemMovie> {
        val movieDetailOutput = MutableLiveData<ResultsItemMovie>()
        CoroutineScope(IO).launch {
            catalogueRemoteDataSource.getMovieDetail(
                movieId,
                object : CatalogueRemoteDataSource.LoadMovieByIdCallback {
                    override fun onMovieDetailReceived(movieCatalogueResponse: ResultsItemMovie) {
                        val moviesDetail = ResultsItemMovie(
                            movieCatalogueResponse.id,
                            movieCatalogueResponse.title,
                            movieCatalogueResponse.posterPath,
                            movieCatalogueResponse.backdropPath,
                            movieCatalogueResponse.releaseDate,
                            movieCatalogueResponse.popularity,
                            movieCatalogueResponse.voteAverage,
                            movieCatalogueResponse.voteCount,
                            movieCatalogueResponse.overview
                        )
                        movieDetailOutput.postValue(moviesDetail)
                        _isLoading.postValue(false)
                    }
                })
        }
        return movieDetailOutput
    }

    override fun getAllFavByTvShows(): LiveData<PagedList<TvShowsEntity>> {
        TODO("Not yet implemented")
    }

    override fun getAllFavByMovies(): LiveData<PagedList<MoviesEntity>> {
        TODO("Not yet implemented")
    }

    override fun setFavMovie(movie: MoviesEntity) {
        TODO("Not yet implemented")
    }

    override fun setFavTvShow(tvShow: TvShowsEntity) {
        TODO("Not yet implemented")
    }
}
