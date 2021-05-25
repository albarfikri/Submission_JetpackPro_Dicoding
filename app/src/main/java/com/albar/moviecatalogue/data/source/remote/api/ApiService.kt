package com.albar.moviecatalogue.data.source.remote.api

import com.albar.moviecatalogue.BuildConfig
import com.albar.moviecatalogue.data.source.remote.response.CatalogueMovieResponse
import com.albar.moviecatalogue.data.source.remote.response.CatalogueTvShowResponse
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemMovie
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemTvShow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("tv/popular")
    fun getTvShowsApi(
        @Query("api_key") apiKey: String = BuildConfig.API_TOKEN
    ): Call<CatalogueTvShowResponse>

    @GET("movie/popular")
    fun getMoviesApi(
        @Query("api_key") apiKey: String = BuildConfig.API_TOKEN
    ): Call<CatalogueMovieResponse>

    @GET("movie/{movie_id}")
    fun getMoviesById(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_TOKEN
    ): Call<ResultsItemMovie>

    @GET("tv/{tv_id}")
    fun getTvShowById(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_TOKEN
    ): Call<ResultsItemTvShow>
}