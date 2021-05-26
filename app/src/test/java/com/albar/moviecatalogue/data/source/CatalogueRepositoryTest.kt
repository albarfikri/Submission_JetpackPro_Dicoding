package com.albar.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.albar.moviecatalogue.data.local.LocalDataSource
import com.albar.moviecatalogue.data.local.entity.MoviesEntity
import com.albar.moviecatalogue.data.local.entity.TvShowsEntity
import com.albar.moviecatalogue.ui.utils.LiveDataTestUtil
import com.albar.moviecatalogue.ui.utils.PagedListUtil
import com.albar.moviecatalogue.utils.DataDummy
import com.albar.moviecatalogue.vo.Resource
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class CatalogueRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val catalogueRemoteDataSource = mock(CatalogueRemoteDataSource::class.java)
    private val localDataSource = mock(LocalDataSource::class.java)
    private val catalogueRepository =
        FakeCatalogueRepository(catalogueRemoteDataSource, localDataSource)

    private val movieResponses = DataDummy.getAllDummyMovie()
    private val tvShowResponses = DataDummy.getAllDummyTvShow()

    private val detailMovie = DataDummy.getAllDummyMovie()[0]
    private val detailTvShow = DataDummy.getAllDummyTvShow()[0]


    @Test
    fun getMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        `when`(localDataSource.getAllMovies()).thenReturn(dataSourceFactory)
        catalogueRepository.getMovies()

        val moviesEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.getAllDummyMovie()))
        verify(localDataSource).getAllMovies()
        org.junit.Assert.assertNotNull(moviesEntity.data)
        assertEquals(movieResponses.size.toLong(), moviesEntity.data?.size?.toLong())
    }

    @Test
    fun getTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowsEntity>
        `when`(localDataSource.getAllTvShows()).thenReturn(dataSourceFactory)
        catalogueRepository.getTvShows()

        val tvShowsEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.getAllDummyTvShow()))
        verify(localDataSource).getAllTvShows()
        org.junit.Assert.assertNotNull(tvShowsEntity.data)
        assertEquals(tvShowResponses.size.toLong(), tvShowsEntity.data?.size?.toLong())
    }

    @Test
    fun getMovieById() {
        val dummyMovie = MutableLiveData<MoviesEntity>()
        dummyMovie.value = detailMovie
        `when`(localDataSource.getDetailMovie(detailMovie.idMovies)).thenReturn(dummyMovie)

        val data = LiveDataTestUtil.getValue(catalogueRepository.getMovieById(detailMovie.idMovies))
        verify(localDataSource).getDetailMovie(detailMovie.idMovies)
        org.junit.Assert.assertNotNull(data)
        assertEquals(detailMovie.idMovies, data.idMovies)
    }

    @Test
    fun getTvShowById() {
        val dummyTvShow = MutableLiveData<TvShowsEntity>()
        dummyTvShow.value = detailTvShow
        `when`(detailTvShow.idTvShow?.let { localDataSource.getDetailTvShows(it) })
            .thenReturn(dummyTvShow)

        val data =
            LiveDataTestUtil.getValue(catalogueRepository.getTvShowById(detailTvShow.idTvShow))
        verify(localDataSource).getDetailTvShows(detailTvShow.idTvShow)
        org.junit.Assert.assertNotNull(data)
        assertEquals(detailMovie.idMovies, data.idTvShow)
    }

    @Test
    fun getAllFavByMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        `when`(localDataSource.getAllFavByMovies()).thenReturn(dataSourceFactory)
        catalogueRepository.getAllFavByMovies()

        val moviesEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.getAllDummyMovie()))
        verify(localDataSource).getAllFavByMovies()
        org.junit.Assert.assertNotNull(moviesEntity.data)
        assertEquals(movieResponses.size.toLong(), moviesEntity.data?.size?.toLong())
    }

    @Test
    fun getAllFavByTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowsEntity>
        `when`(localDataSource.getAllFavByTvShows()).thenReturn(dataSourceFactory)
        catalogueRepository.getAllFavByTvShows()

        val tvShowEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.getAllDummyTvShow()))
        verify(localDataSource).getAllFavByTvShows()
        org.junit.Assert.assertNotNull(tvShowEntity.data)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntity.data?.size?.toLong())
    }
}