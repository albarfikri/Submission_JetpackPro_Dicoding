package com.albar.moviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.albar.moviecatalogue.data.source.CatalogueRepository
import com.albar.moviecatalogue.ui.detailcatalogue.CatalogueDetailViewModel
import com.albar.moviecatalogue.ui.favorite.FavViewModel
import com.albar.moviecatalogue.ui.main.movie.MovieViewModel
import com.albar.moviecatalogue.ui.main.tvshow.TvShowViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val catalogueRepository: CatalogueRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(catalogueRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(catalogueRepository) as T
            }
            modelClass.isAssignableFrom(CatalogueDetailViewModel::class.java) -> {
                CatalogueDetailViewModel(catalogueRepository) as T
            }
            modelClass.isAssignableFrom(FavViewModel::class.java) -> {
                FavViewModel(catalogueRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class" + modelClass.name)
        }
    }
}