package com.albar.moviecatalogue.di.module

import android.app.Application
import com.albar.moviecatalogue.data.local.LocalDataSource
import com.albar.moviecatalogue.data.local.room.CatalogueDao
import com.albar.moviecatalogue.data.local.room.CatalogueDatabase
import com.albar.moviecatalogue.data.source.CatalogueRemoteDataSource
import com.albar.moviecatalogue.data.source.CatalogueRepository
import com.albar.moviecatalogue.data.source.remote.api.ApiService
import com.albar.moviecatalogue.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CatalogueApp {
    companion object {
        @Singleton
        @Provides
        fun catalogueDbDi(application: Application): CatalogueDatabase =
            CatalogueDatabase.getInstance(application)

        @Singleton
        @Provides
        fun catalogueDaoDi(catalogueDatabase: CatalogueDatabase): CatalogueDao =
            catalogueDatabase.catalogueDao()

        @Singleton
        @Provides
        fun localDataSourceDi(catalogDao: CatalogueDao): LocalDataSource =
            LocalDataSource(catalogDao)

        @Singleton
        @Provides
        fun remoteDataSourceDi(apiService: ApiService): CatalogueRemoteDataSource =
            CatalogueRemoteDataSource(apiService)

        @Singleton
        @Provides
        fun catalogueRepositoryDi(
            catalogueRemoteDataSource: CatalogueRemoteDataSource,
            localDataSource: LocalDataSource
        ): CatalogueRepository = CatalogueRepository(catalogueRemoteDataSource, localDataSource)

        @Singleton
        @Provides
        fun provideViewModelFactory(catalogRepository: CatalogueRepository): ViewModelFactory =
            ViewModelFactory(catalogRepository)
    }
}