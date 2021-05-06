package com.albar.moviecatalogue.ui.detailcatalogue

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.albar.moviecatalogue.data.CatalogueDataModel
import com.albar.moviecatalogue.data.CatalogueEntity
import com.albar.moviecatalogue.data.source.CatalogueDataSource
import com.albar.moviecatalogue.data.source.CatalogueRepository
import com.albar.moviecatalogue.utils.DataDummy

class CatalogueDetailViewModel(private val repository: CatalogueRepository) : ViewModel() {
//    private lateinit var movieId: String
//    private lateinit var tvShowId: String
//
//    fun getCatalogue(catalogueId: String) {
//        this.movieId = catalogueId
//        this.tvShowId = catalogueId
//    }
//
//    fun getAllMovie(): CatalogueEntity {
//        lateinit var detail: CatalogueEntity
//        val detailEntities = DataDummy.getAllDummyMovie()
//        for (detailEntity in detailEntities) {
//            if (detailEntity.id == movieId) {
//                detail = detailEntity
//            }
//        }
//        return detail
//    }
//
//    fun getAllTvShow(): CatalogueEntity {
//        lateinit var detail: CatalogueEntity
//        val detailEntities = DataDummy.getAllDummyTvShow()
//        for (detailEntity in detailEntities) {
//            if (detailEntity.id == tvShowId) {
//                detail = detailEntity
//            }
//        }
//        return detail
//    }

    fun getMovieDetailById(movieId: Int): LiveData<CatalogueDataModel> =
        repository.getMovieById(movieId)

    fun getTvShowDetailById(tvShowsId: Int): LiveData<CatalogueDataModel> = repository.getTvShowById(tvShowsId)

}