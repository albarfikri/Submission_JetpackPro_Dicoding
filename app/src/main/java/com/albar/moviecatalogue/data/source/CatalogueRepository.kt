package com.albar.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.albar.moviecatalogue.data.CatalogueDataModel
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemMovie
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemTvShow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class CatalogueRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    CatalogueDataSource {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        @Volatile
        private var instance: CatalogueRepository? = null


        fun getInstance(remoteData: RemoteDataSource): CatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogueRepository(remoteData).apply { instance = this }
            }

    }

    override fun getMovie(): LiveData<List<CatalogueDataModel>> {
        val listMoviesOutput = MutableLiveData<List<CatalogueDataModel>>()
        _isLoading.value = true
        CoroutineScope(IO).launch {
            remoteDataSource.getMovies(object : RemoteDataSource.LoadMovieCallback {
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

    override fun getMovieById(movieId: Int): LiveData<CatalogueDataModel> {
        TODO("Not yet implemented")
    }

    override fun getTvShow(): LiveData<List<CatalogueDataModel>> {
        val listMoviesOutput = MutableLiveData<List<CatalogueDataModel>>()
        val listTvShowOutput = MutableLiveData<List<CatalogueDataModel>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getTvShows(object : RemoteDataSource.LoadTvShowCallback {
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

    override fun getTvShowById(tvshowId: Int): LiveData<CatalogueDataModel> {
        TODO("Not yet implemented")
    }


}