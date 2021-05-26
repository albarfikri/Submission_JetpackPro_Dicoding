package com.albar.moviecatalogue.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.albar.moviecatalogue.data.local.entity.MoviesEntity
import com.albar.moviecatalogue.data.local.entity.TvShowsEntity
import com.albar.moviecatalogue.data.source.CatalogueRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavViewModelTest {
    private lateinit var favViewModel: FavViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observerMovies: Observer<PagedList<MoviesEntity>>

    @Mock
    private lateinit var observerTvShow: Observer<PagedList<TvShowsEntity>>

    @Mock
    private lateinit var moviesPagedList: PagedList<MoviesEntity>

    @Mock
    private lateinit var tvShowPagedList: PagedList<TvShowsEntity>

    @Before
    fun setUp() {
        favViewModel = FavViewModel(catalogueRepository)
    }

    @Test
    fun getFavMovies() {
        val getAllDummyMovie = moviesPagedList
        Mockito.`when`(getAllDummyMovie.size).thenReturn(5)
        val movies = MutableLiveData<PagedList<MoviesEntity>>()
        movies.value = getAllDummyMovie

        Mockito.`when`(catalogueRepository.getAllFavByMovies()).thenReturn(movies)
        val moviesEntity = favViewModel.getFavMovies().value
        Mockito.verify(catalogueRepository).getAllFavByMovies()
        Assert.assertNotNull(moviesEntity)
        Assert.assertEquals(5, moviesEntity?.size)

        favViewModel.getFavMovies().observeForever(observerMovies)
        Mockito.verify(observerMovies).onChanged(getAllDummyMovie)
    }

    @Test
    fun getFavTvShows() {
        val getAllDummyTvShow = tvShowPagedList
        Mockito.`when`(getAllDummyTvShow.size).thenReturn(5)
        val tvshows = MutableLiveData<PagedList<TvShowsEntity>>()
        tvshows.value = getAllDummyTvShow

        Mockito.`when`(catalogueRepository.getAllFavByTvShows()).thenReturn(tvshows)
        val tvShowsEntity = favViewModel.getFavTvShow().value
        Mockito.verify(catalogueRepository).getAllFavByTvShows()
        Assert.assertNotNull(tvShowsEntity)
        Assert.assertEquals(5, tvShowsEntity?.size)

        favViewModel.getFavTvShow().observeForever(observerTvShow)
        Mockito.verify(observerTvShow).onChanged(getAllDummyTvShow)
    }
}