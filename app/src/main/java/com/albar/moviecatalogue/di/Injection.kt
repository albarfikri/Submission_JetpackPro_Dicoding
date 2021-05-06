package com.albar.moviecatalogue.di

import com.albar.moviecatalogue.data.source.CatalogueRepository
import com.albar.moviecatalogue.data.source.CatalogueRemoteDataSource

object Injection {
    fun provideRepository(): CatalogueRepository {
        val remoteDataSource = CatalogueRemoteDataSource.getInstance()
        return CatalogueRepository.getInstance(remoteDataSource)
    }
}