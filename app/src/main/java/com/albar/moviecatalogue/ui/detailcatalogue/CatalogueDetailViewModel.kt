package com.albar.moviecatalogue.ui.detailcatalogue

import androidx.lifecycle.ViewModel
import com.albar.moviecatalogue.data.CatalogueEntity
import com.albar.moviecatalogue.utils.DataDummy

class CatalogueDetailViewModel : ViewModel() {
    private lateinit var movieId: String
    private lateinit var tvShowId: String

    fun getCatalogue(catalogueId: String) {
        this.movieId = catalogueId
        this.tvShowId = catalogueId
    }

    fun getAllMovie(): CatalogueEntity {
        lateinit var detail: CatalogueEntity
        val detailEntities = DataDummy.getAllDummyMovie()
        for (detailEntity in detailEntities) {
            if (detailEntity.id == movieId) {
                detail = detailEntity
            }
        }
        return detail
    }

    fun getAllTvShow(): CatalogueEntity {
        lateinit var detail: CatalogueEntity
        val detailEntities = DataDummy.getAllDummyTvShow()
        for (detailEntity in detailEntities) {
            if (detailEntity.id == tvShowId) {
                detail = detailEntity
            }
        }
        return detail
    }

}