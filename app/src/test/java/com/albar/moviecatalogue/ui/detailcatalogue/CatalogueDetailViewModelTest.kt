package com.albar.moviecatalogue.ui.detailcatalogue

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.albar.moviecatalogue.data.source.CatalogueRepository
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemMovie
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemTvShow
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
class CatalogueDetailViewModelTest {

    private lateinit var viewModel: CatalogueDetailViewModel
    private val dummyMovie = DataDummy.getAllDummyMovie()[2]
    private val dummyTvShow = DataDummy.getAllDummyTvShow()[2]
    private val movieIdCatalogue = dummyMovie.id
    private val tvShowIdCatalogue = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var movieObserver: Observer<ResultsItemMovie>

    @Mock
    private lateinit var tvShowObserver: Observer<ResultsItemTvShow>

    @Before
    fun setUp() {
        viewModel = CatalogueDetailViewModel(catalogueRepository)
    }

    @Test
    fun getAllMoviesById() {
        val movies = MutableLiveData<ResultsItemMovie>()

        movies.value = dummyMovie
        `when`(movieIdCatalogue.let { catalogueRepository.getMovieById(it) }).thenReturn(movies)

        val listMovieData =
            movieIdCatalogue.let { viewModel.getMovieDetailById(it) }.value as ResultsItemMovie

        assertNotNull(listMovieData)
        assertEquals(dummyMovie.id, listMovieData.id)
        assertEquals(dummyMovie.posterPath, listMovieData.posterPath)
        assertEquals(dummyMovie.voteAverage, listMovieData.voteAverage)
        assertEquals(dummyMovie.backdropPath, listMovieData.backdropPath)
        assertEquals(dummyMovie.overview, listMovieData.overview)
        assertEquals(dummyMovie.popularity, listMovieData.popularity)
        assertEquals(dummyMovie.releaseDate, listMovieData.releaseDate)
        assertEquals(dummyMovie.title, listMovieData.title)
        assertEquals(dummyMovie.voteCount, listMovieData.voteCount)

        viewModel.getMovieDetailById(movieIdCatalogue).observeForever(movieObserver)

        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getAllTvShowsById() {
        val tvShows = MutableLiveData<ResultsItemTvShow>()

        tvShows.value = dummyTvShow
        `when`(tvShowIdCatalogue.let { catalogueRepository.getTvShowById(it) }).thenReturn(tvShows)

        val listTvShowsData =
            tvShowIdCatalogue.let { viewModel.getTvShowDetailById(it) }.value as ResultsItemTvShow

        assertNotNull(listTvShowsData)
        assertEquals(dummyTvShow.id, listTvShowsData.id)
        assertEquals(dummyTvShow.posterPath, listTvShowsData.posterPath)
        assertEquals(dummyTvShow.voteAverage, listTvShowsData.voteAverage)
        assertEquals(dummyTvShow.backdropPath, listTvShowsData.backdropPath)
        assertEquals(dummyTvShow.overview, listTvShowsData.overview)
        assertEquals(dummyTvShow.popularity, listTvShowsData.popularity)
        assertEquals(dummyTvShow.firstAirDate, listTvShowsData.firstAirDate)
        assertEquals(dummyTvShow.originalName, listTvShowsData.originalName)
        assertEquals(dummyTvShow.voteCount, listTvShowsData.voteCount)

        viewModel.getTvShowDetailById(tvShowIdCatalogue).observeForever(tvShowObserver)

        verify(tvShowObserver).onChanged(dummyTvShow)
    }
}
