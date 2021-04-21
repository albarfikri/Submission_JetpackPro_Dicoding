package com.albar.moviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import com.albar.moviecatalogue.data.CatalogueEntity
import com.albar.moviecatalogue.utils.DataDummy

class MovieViewModel : ViewModel() {
    fun getAllMovieDummy(): List<CatalogueEntity> = DataDummy.getAllDummyMovie()
}