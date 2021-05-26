package com.albar.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.albar.moviecatalogue.data.local.entity.MoviesEntity
import com.albar.moviecatalogue.data.local.entity.TvShowsEntity
import com.albar.moviecatalogue.data.source.CatalogueRepository
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemTvShow
import com.albar.moviecatalogue.ui.main.movie.MovieViewModel
import com.albar.moviecatalogue.ui.main.tvshow.TvShowViewModel
import com.albar.moviecatalogue.utils.DataDummy
import com.albar.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
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
class TvShowViewModelTest {
    private lateinit var tvShowViewModel : TvShowViewModel

    @get:Rule
    // pengujian asynchronous
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observerTvShows: Observer<Resource<PagedList<TvShowsEntity>>>

    @Mock
    private lateinit var tvShowPagedList: PagedList<TvShowsEntity>


    @Before
    fun setUp() {
        tvShowViewModel = TvShowViewModel(catalogueRepository)
    }

    @Test
    fun getMovie() {
        val getAllDummyTvShow = Resource.success(tvShowPagedList)
        `when`(getAllDummyTvShow.data?.size).thenReturn(5)
        val tvShows = MutableLiveData<Resource<PagedList<TvShowsEntity>>>()
        tvShows.value = getAllDummyTvShow

        `when`(catalogueRepository.getTvShows()).thenReturn(tvShows)
        val tvShowsEntity = tvShowViewModel.getAllTvShowList().value?.data
        Mockito.verify(catalogueRepository).getTvShows()
        Assert.assertNotNull(tvShowsEntity)
        Assert.assertEquals(5, tvShowsEntity?.size)

        tvShowViewModel.getAllTvShowList().observeForever(observerTvShows)
        Mockito.verify(observerTvShows).onChanged(getAllDummyTvShow)
    }
}