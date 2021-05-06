package com.albar.moviecatalogue.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.albar.moviecatalogue.data.CatalogueDataModel
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemMovie
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemTvShow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class CatalogueRepository private constructor(private val catalogueRemoteDataSource: CatalogueRemoteDataSource) :
    CatalogueDataSource {

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

    override fun getMovie(): LiveData<List<CatalogueDataModel>> {
        val listMoviesOutput = MutableLiveData<List<CatalogueDataModel>>()
        _isLoading.value = true
        CoroutineScope(IO).launch {
            catalogueRemoteDataSource.getMovies(object :
                CatalogueRemoteDataSource.LoadMovieCallback {
                override fun onAllMovieReceived(movieCatalogueResponse: List<ResultsItemMovie>) {
                    val listMovies = ArrayList<CatalogueDataModel>()
                    for (response in movieCatalogueResponse) {
                        val movie = CatalogueDataModel(
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

    override fun getTvShow(): LiveData<List<CatalogueDataModel>> {
        val listTvShowOutput = MutableLiveData<List<CatalogueDataModel>>()
        CoroutineScope(IO).launch {
            catalogueRemoteDataSource.getTvShows(object :
                CatalogueRemoteDataSource.LoadTvShowCallback {
                override fun onAllTvShowReceived(tvShowCatalogueResponse: List<ResultsItemTvShow>) {
                    val listTvShow = ArrayList<CatalogueDataModel>()
                    for (response in tvShowCatalogueResponse) {
                        val tvShows = CatalogueDataModel(
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

    override fun getTvShowById(tvShowId: Int): LiveData<CatalogueDataModel> {
        val tvShowDetailOutput = MutableLiveData<CatalogueDataModel>()
        CoroutineScope(IO).launch {
            catalogueRemoteDataSource.getTvShowDetail(
                tvShowId,
                object : CatalogueRemoteDataSource.LoadTvShowsByIdCallback {
                    override fun onTvShowsDetailReceived(tvShowsCatalogue: ResultsItemTvShow) {
                        val tvShowsDetail = CatalogueDataModel(
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
                    }
                })
        }
        return tvShowDetailOutput
    }

    override fun getMovieById(movieId: Int): LiveData<CatalogueDataModel> {
        val movieDetailOutput = MutableLiveData<CatalogueDataModel>()
        CoroutineScope(IO).launch {
            catalogueRemoteDataSource.getMovieDetail(
                movieId,
                object : CatalogueRemoteDataSource.LoadMovieByIdCallback {
                    override fun onMovieDetailReceived(movieCatalogueResponse: ResultsItemMovie) {
                        val moviesDetail = CatalogueDataModel(
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
                    }
                })
        }
        return movieDetailOutput
    }
}
