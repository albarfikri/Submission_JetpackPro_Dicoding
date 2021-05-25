package com.albar.moviecatalogue.di.module

import com.albar.moviecatalogue.BuildConfig
import com.albar.moviecatalogue.data.source.remote.api.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class Network {
    companion object {
        @Singleton
        @Provides
        fun httpClientDi(): OkHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
        }.build()

        @Singleton
        @Provides
        fun retrofitDi(okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder().apply {
                baseUrl(BuildConfig.API_URL)
                client(okHttpClient)
                addConverterFactory(GsonConverterFactory.create())
            }.build()

        @Provides
        fun catalogApiDi(retrofit: Retrofit): ApiService =
            retrofit.create(ApiService::class.java)
    }
}