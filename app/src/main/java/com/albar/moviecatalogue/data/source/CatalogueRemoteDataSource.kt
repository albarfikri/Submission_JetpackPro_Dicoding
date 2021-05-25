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