package com.albar.moviecatalogue.ui.detailcatalogue

import androidx.lifecycle.ViewModel
import com.albar.moviecatalogue.data.CatalogueEntity
import com.albar.moviecatalogue.utils.DataDummy

class CatalogueDetailViewModel : ViewModel() {
    private lateinit var catalogueId: String

    fun getMovieById(catalogueId: String) {
        this.catalogueId = catalogueId
    }

    fun getAllMovie(): CatalogueEntity {
        lateinit var detail: CatalogueEntity
        val detailEntities = DataDummy.getAllDummyMovie()
        for (detailEntity in detailEntities) {
            if (detailEntity.id == catalogueId) {
                detail = detailEntity
            }
        }
        return detail
    }

    fun getAllTvShow(): CatalogueEntity {
        lateinit var detail: CatalogueEntity
        val detailEntities = DataDummy.generateDummyTvShow()
        for (detailEntity in detailEntities) {
            if (detailEntity.id == catalogueId) {
                detail = detailEntity
            }
        }
        return detail
    }
}