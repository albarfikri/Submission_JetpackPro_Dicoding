package com.albar.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.albar.moviecatalogue.data.local.entity.MoviesEntity
import com.albar.moviecatalogue.data.source.CatalogueRepository
import com.albar.moviecatalogue.ui.main.movie.MovieViewModel
import com.albar.moviecatalogue.vo.Resource
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var movieViewModel: MovieViewModel

    @get:Rule
    // pengujian asynchronous
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observerMovie: Observer<Resource<PagedList<MoviesEntity>>>

    @Mock
    private lateinit var moviesPagedList: PagedList<MoviesEntity>


    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(catalogueRepository)
    }

    @Test
    fun getMovie() {
        val getAllDummyMovies = Resource.success(moviesPagedList)
        `when`(getAllDummyMovies.data?.size).thenReturn(5)
        val movies = MutableLiveData<Resource<PagedList<MoviesEntity>>>()
        movies.value = getAllDummyMovies

        `when`(catalogueRepository.getMovies()).thenReturn(movies)
        val moviesEntity = movieViewModel.getAllMoviesList().value?.data
        Mockito.verify(catalogueRepository).getMovies()
        Assert.assertNotNull(moviesEntity)
        Assert.assertEquals(5, moviesEntity?.size)

        movieViewModel.getAllMoviesList().observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(getAllDummyMovies)
    }
}