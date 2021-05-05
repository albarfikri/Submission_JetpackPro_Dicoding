package com.albar.moviecatalogue.data.source.remote.api

import com.albar.moviecatalogue.BuildConfig
import com.albar.moviecatalogue.data.source.remote.response.CatalogueMovieResponse
import com.albar.moviecatalogue.data.source.remote.response.CatalogueTvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("tv/popular")
    fun getTvShowsApi(
       @Query("api_key") apiKey:String = BuildConfig.API_TOKEN
    ): Call<CatalogueTvShowResponse>

    @GET("movie/popular")
    fun getMoviesApi(
       @Query("api_key") apiKey:String = BuildConfig.API_TOKEN
    ): Call<CatalogueMovieResponse>


}