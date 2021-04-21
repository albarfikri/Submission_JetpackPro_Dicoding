package com.albar.moviecatalogue.ui.tvshow

import androidx.lifecycle.ViewModel
import com.albar.moviecatalogue.data.CatalogueEntity
import com.albar.moviecatalogue.utils.DataDummy

class TvShowViewModel : ViewModel() {
    fun getAllTvShowDummy(): List<CatalogueEntity> = DataDummy.generateDummyTvShow()
}