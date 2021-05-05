package com.albar.moviecatalogue.data.source

import android.os.Handler
import android.os.Looper
import com.albar.moviecatalogue.data.source.remote.api.ApiConfig
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemMovie
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemTvShow
import com.albar.moviecatalogue.utils.IdlingResource
import retrofit2.await

class RemoteDataSource {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val delayTime: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    suspend fun getMovies(callback: LoadMovieCallback) {
        IdlingResource.increment()
        ApiConfig.getApiService().getMoviesApi().await().results.let { getListMovies ->
            callback.onAllMovieReceived(
                getListMovies
            )
            IdlingResource.decrement()
        }
    }

    suspend fun getTvShows(callback: LoadTvShowCallback) {
        IdlingResource.increment()
        ApiConfig.getApiService().getTvShowsApi().await().results.let { getListTvShows ->
            callback.onAllTvShowReceived(
                getListTvShows
            )
            IdlingResource.decrement()
        }
    }

    interface LoadMovieCallback {
        fun onAllMovieReceived(movieCatalogueResponse: List<ResultsItemMovie>)
    }

    interface LoadTvShowCallback {
        fun onAllTvShowReceived(tvShowCatalogueResponse: List<ResultsItemTvShow>)
    }
}