package com.albar.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.albar.moviecatalogue.ui.utils.LiveDataTestUtil
import com.albar.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.mock

class CatalogueRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    private val remoteCatalogue = mock(CatalogueRemoteDataSource::class.java)
    private val catalogueRepository = FakeCatalogueRepository(remoteCatalogue)
    private val movieResponses = DataDummy.getAllDummyMovie()
    private val movieId = movieResponses[0].id
    private val tvShowResponses = DataDummy.getAllDummyTvShow()
    private val tvShowId = tvShowResponses[0].id
    private val detailMovie = DataDummy.getAllDummyMovie()[0]
    private val detailTvShow = DataDummy.getAllDummyTvShow()[0]


    @Test
    fun getAllMovies() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as CatalogueRemoteDataSource.LoadMovieCallback).onAllMovieReceived(
                    movieResponses
                )
                null
            }.`when`(remoteCatalogue).getMovies(any())
        }

        val liveData = LiveDataTestUtil.getValue(catalogueRepository.getMovie())

        runBlocking {
            verify(remoteCatalogue).getMovies(any())
        }

        assertNotNull(liveData)
        assertEquals(movieResponses.size.toLong(), liveData.size.toLong())
    }

    @Test
    fun getAllMoviesById() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as CatalogueRemoteDataSource.LoadMovieByIdCallback)
                    .onMovieDetailReceived(detailMovie)
                null
            }.`when`(remoteCatalogue).getMovieDetail(eq(movieId), any())
        }

        val liveData = LiveDataTestUtil.getValue(catalogueRepository.getMovieById(movieId))

        runBlocking {
            verify(remoteCatalogue).getMovieDetail(eq(movieId), any())
        }

        assertNotNull(liveData)
        assertEquals(detailMovie.id, liveData.id)
    }

    @Test
    fun getAllTvShows() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as CatalogueRemoteDataSource.LoadTvShowCallback).onAllTvShowReceived(
                    tvShowResponses
                )
                null
            }.`when`(remoteCatalogue).getTvShows(any())

            val liveData = LiveDataTestUtil.getValue(catalogueRepository.getTvShow())

            runBlocking {
                verify(remoteCatalogue).getTvShows(any())
            }
            assertNotNull(liveData)
            assertEquals(tvShowResponses.size.toLong(), liveData.size.toLong())
        }
    }

    @Test
    fun getAllTvShowsById() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as CatalogueRemoteDataSource.LoadTvShowsByIdCallback)
                    .onTvShowsDetailReceived(detailTvShow)
                null
            }.`when`(remoteCatalogue).getTvShowDetail(eq(tvShowId), any())
        }

        val liveData = LiveDataTestUtil.getValue(catalogueRepository.getTvShowById(movieId))

        runBlocking {
            verify(remoteCatalogue).getTvShowDetail(eq(tvShowId), any())
        }

        assertNotNull(liveData)
        assertEquals(detailTvShow.id, liveData.id)
    }

}