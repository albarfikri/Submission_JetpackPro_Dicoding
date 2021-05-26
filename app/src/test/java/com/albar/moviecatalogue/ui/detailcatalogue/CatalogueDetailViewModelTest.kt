package com.albar.moviecatalogue.ui.detailcatalogue

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.albar.moviecatalogue.data.local.entity.MoviesEntity
import com.albar.moviecatalogue.data.local.entity.TvShowsEntity
import com.albar.moviecatalogue.data.source.CatalogueRepository
import com.albar.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CatalogueDetailViewModelTest {

    private lateinit var viewModel: CatalogueDetailViewModel
    private val dummyMovie = DataDummy.getAllDummyMovie()[2]
    private val dummyTvShow = DataDummy.getAllDummyTvShow()[2]
    private val movieIdCatalogue = dummyMovie.idMovies
    private val tvShowIdCatalogue = dummyTvShow.idTvShow

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var movieObserver: Observer<MoviesEntity>

    @Mock
    private lateinit var tvShowObserver: Observer<TvShowsEntity>

    @Before
    fun setUp() {
        viewModel = CatalogueDetailViewModel(catalogueRepository)
    }

    @Test
    fun getAllMoviesById() {
        val movies = MutableLiveData<MoviesEntity>()
        movies.value = dummyMovie

        Mockito.`when`(catalogueRepository.getMovieById(movieIdCatalogue)).thenReturn(movies)

        val moviesData = viewModel.getMovieDetailById(movieIdCatalogue).value


        assertNotNull(moviesData)
        assertEquals(dummyMovie.idMovies, moviesData?.idMovies)
        assertEquals(dummyMovie.releaseDate, moviesData?.releaseDate)
        assertEquals(dummyMovie.voteAverage, moviesData?.voteAverage)
        assertEquals(dummyMovie.overview, moviesData?.overview)
        assertEquals(dummyMovie.posterPath, moviesData?.posterPath)
        assertEquals(dummyMovie.backDropPath, moviesData?.backDropPath)
        assertEquals(dummyMovie.title, moviesData?.title)
        assertEquals(dummyMovie.isFavorited, moviesData?.isFavorited)

        viewModel.getMovieDetailById(movieIdCatalogue).observeForever(movieObserver)

        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getAllTvShowsById() {
        val tvShows = MutableLiveData<TvShowsEntity>()
        tvShows.value = dummyTvShow

        Mockito.`when`(catalogueRepository.getTvShowById(tvShowIdCatalogue)).thenReturn(tvShows)

        val tvShowsData = viewModel.getTvShowDetailById(movieIdCatalogue).value


        assertNotNull(tvShowsData)
        assertEquals(dummyTvShow.idTvShow, tvShowsData?.idTvShow)
        assertEquals(dummyTvShow.firstAirDate, tvShowsData?.firstAirDate)
        assertEquals(dummyTvShow.voteAverage, tvShowsData?.voteAverage)
        assertEquals(dummyTvShow.overview, tvShowsData?.overview)
        assertEquals(dummyTvShow.posterPath, tvShowsData?.posterPath)
        assertEquals(dummyTvShow.backDropPath, tvShowsData?.backDropPath)
        assertEquals(dummyTvShow.originalName, tvShowsData?.originalName)
        assertEquals(dummyTvShow.isFavorited, tvShowsData?.isFavorited)

        viewModel.getTvShowDetailById(tvShowIdCatalogue).observeForever(tvShowObserver)

        verify(tvShowObserver).onChanged(dummyTvShow)
    }
}
