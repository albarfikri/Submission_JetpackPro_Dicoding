package com.albar.moviecatalogue.data.source

import com.albar.moviecatalogue.data.source.remote.api.ApiConfig
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemMovie
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemTvShow
import com.albar.moviecatalogue.utils.IdlingResource
import retrofit2.await

class CatalogueRemoteDataSource {

    companion object {
        @Volatile
        private var instance: CatalogueRemoteDataSource? = null

        fun getInstance(): CatalogueRemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: CatalogueRemoteDataSource()
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

    suspend fun getMovieDetail(movieId: Int, callback: LoadMovieByIdCallback) {
        IdlingResource.increment()
        ApiConfig.getApiService().getMoviesById(movieId).await().let { movieById ->
            callback.onMovieDetailReceived(
                movieById
            )
            IdlingResource.decrement()
        }
    }

    suspend fun getTvShowDetail(tvShowsId: Int, callback: LoadTvShowsByIdCallback) {
        IdlingResource.increment()
        ApiConfig.getApiService().getTvShowById(tvShowsId).await().let { tvShows ->
            callback.onTvShowsDetailReceived(
                tvShows
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

    interface LoadMovieByIdCallback {
        fun onMovieDetailReceived(movieCatalogueResponse: ResultsItemMovie)
    }

    interface LoadTvShowsByIdCallback {
        fun onTvShowsDetailReceived(tvShowsCatalogue: ResultsItemTvShow)
    }
}
