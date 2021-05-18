package com.albar.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.albar.moviecatalogue.data.source.remote.api.ApiService
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemMovie
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemTvShow
import com.albar.moviecatalogue.data.source.remote.response.vo.ApiResponse
import com.albar.moviecatalogue.utils.IdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException
import javax.inject.Inject

class CatalogueRemoteDataSource @Inject constructor(private val apiService: ApiService) {

    fun getMovies(): LiveData<ApiResponse<List<ResultsItemMovie>>> {
        IdlingResource.increment()
        val movieResponseResult = MutableLiveData<ApiResponse<List<ResultsItemMovie>>>()
        CoroutineScope(IO).launch {
            try {
                val response = apiService.getMoviesApi().await()
                movieResponseResult.postValue(ApiResponse.success(response.results))
            } catch (e: IOException) {
                e.printStackTrace()
                movieResponseResult.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        IdlingResource.decrement()
        return movieResponseResult
    }

    fun getTvShows(): LiveData<ApiResponse<List<ResultsItemTvShow>>> {
        IdlingResource.increment()
        val tvShowResponseResult = MutableLiveData<ApiResponse<List<ResultsItemTvShow>>>()
        CoroutineScope(IO).launch {
            try {
                val response = apiService.getTvShowsApi().await()
                tvShowResponseResult.postValue(ApiResponse.success(response.results))
            } catch (e: IOException) {
                e.printStackTrace()
                tvShowResponseResult.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        IdlingResource.decrement()
        return tvShowResponseResult
    }
}

//    suspend fun getMovies(callback: LoadMovieCallback) {
//        IdlingResource.increment()
//        ApiConfig.getApiService().getMoviesApi().await().results.let { getListMovies ->
//            callback.onAllMovieReceived(
//                getListMovies
//            )
//            IdlingResource.decrement()
//        }
//    }
//
//    suspend fun getTvShows(callback: LoadTvShowCallback) {
//        IdlingResource.increment()
//        ApiConfig.getApiService().getTvShowsApi().await().results.let { getListTvShows ->
//            callback.onAllTvShowReceived(
//                getListTvShows
//            )
//            IdlingResource.decrement()
//        }
//    }
//
//    suspend fun getMovieDetail(movieId: Int, callback: LoadMovieByIdCallback) {
//        IdlingResource.increment()
//        ApiConfig.getApiService().getMoviesById(movieId).await().let { movieById ->
//            callback.onMovieDetailReceived(
//                movieById
//            )
//            IdlingResource.decrement()
//        }
//    }
//
//    suspend fun getTvShowDetail(tvShowsId: Int, callback: LoadTvShowsByIdCallback) {
//        IdlingResource.increment()
//        ApiConfig.getApiService().getTvShowById(tvShowsId).await().let { tvShows ->
//            callback.onTvShowsDetailReceived(
//                tvShows
//            )
//            IdlingResource.decrement()
//        }
//    }
//
//    interface LoadMovieCallback {
//        fun onAllMovieReceived(movieCatalogueResponse: List<ResultsItemMovie>)
//    }
//
//    interface LoadTvShowCallback {
//        fun onAllTvShowReceived(tvShowCatalogueResponse: List<ResultsItemTvShow>)
//    }
//
//    interface LoadMovieByIdCallback {
//        fun onMovieDetailReceived(movieCatalogueResponse: ResultsItemMovie)
//    }
//
//    interface LoadTvShowsByIdCallback {
//        fun onTvShowsDetailReceived(tvShowsCatalogue: ResultsItemTvShow)
//    }
//}
