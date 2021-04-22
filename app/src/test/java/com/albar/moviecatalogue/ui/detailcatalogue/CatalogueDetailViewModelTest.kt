package com.albar.moviecatalogue.ui.detailcatalogue

import com.albar.moviecatalogue.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class CatalogueDetailViewModelTest {
    private lateinit var viewModel: CatalogueDetailViewModel
    private val dummyMovie = DataDummy.getAllDummyMovie()[0]
    private val dummyTvShow = DataDummy.getAllDummyTvShow()[0]
    private val dummyCatalogueId = dummyMovie.id


    @Before
    fun setUp() {
        viewModel = CatalogueDetailViewModel()
        if (dummyCatalogueId != null) {
            viewModel.getCatalogue(this.dummyCatalogueId)
        }
    }

    @Test
    fun getAllMovie() {
        dummyMovie.id?.let { viewModel.getCatalogue(it) }
        val movieEntity = viewModel.getAllMovie()
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity.id)
        assertEquals(dummyMovie.movieName, movieEntity.movieName)
        assertEquals(dummyMovie.image, movieEntity.image)
        assertEquals(dummyMovie.imageBackground, movieEntity.imageBackground)
        assertEquals(dummyMovie.release, movieEntity.release)
        assertEquals(dummyMovie.review, movieEntity.review)
        assertEquals(dummyMovie.rating, movieEntity.rating)
        assertEquals(dummyMovie.duration, movieEntity.duration)
        assertEquals(dummyMovie.price, movieEntity.price)
        assertEquals(dummyMovie.about, movieEntity.about)
    }

    @Test
    fun getAllTvShow() {
        dummyTvShow.id?.let{ viewModel.getCatalogue(it) }
        val tvShowEntity = viewModel.getAllTvShow()
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.id, tvShowEntity.id)
        assertEquals(dummyTvShow.movieName, tvShowEntity.movieName)
        assertEquals(dummyTvShow.image, tvShowEntity.image)
        assertEquals(dummyTvShow.imageBackground, tvShowEntity.imageBackground)
        assertEquals(dummyTvShow.release, tvShowEntity.release)
        assertEquals(dummyTvShow.review, tvShowEntity.review)
        assertEquals(dummyTvShow.rating, tvShowEntity.rating)
        assertEquals(dummyTvShow.duration, tvShowEntity.duration)
        assertEquals(dummyTvShow.price, tvShowEntity.price)
        assertEquals(dummyTvShow.about, tvShowEntity.about)

    }

}
