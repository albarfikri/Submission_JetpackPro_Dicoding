package com.albar.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.albar.moviecatalogue.data.source.CatalogueRepository
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemMovie
import com.albar.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    // pengujian asynchronous
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<ResultsItemMovie>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(catalogueRepository)
    }

    @Test
    fun getMovie() {
        val dummyMoviesList = DataDummy.getAllDummyMovie()
        val movie = MutableLiveData<List<ResultsItemMovie>>()
        movie.value = dummyMoviesList

        `when`(catalogueRepository.getMovie()).thenReturn(movie)
        val movieList = viewModel.getAllMoviesList().value
        verify(catalogueRepository).getMovie()
        assertNotNull(movie)
        if (movieList != null) {
            assertEquals(20, movieList.size)
        }

        viewModel.getAllMoviesList().observeForever(observer)
        verify(observer).onChanged(dummyMoviesList)
    }
}