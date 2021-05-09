package com.albar.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.albar.moviecatalogue.data.source.CatalogueRepository
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
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    // Asynchronous
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<ResultsItemTvShow>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(catalogueRepository)
    }

    @Test
    fun getTvShow() {
        val dummyTvShowSList = DataDummy.getAllDummyTvShow()
        val tvShow = MutableLiveData<List<ResultsItemTvShow>>()
        tvShow.value = dummyTvShowSList

        `when`(catalogueRepository.getTvShow()).thenReturn(tvShow)
        val tvShowList = viewModel.getAllTvShowsList().value
        verify(catalogueRepository).getTvShow()
        assertNotNull(tvShowList)
        if (tvShowList != null) {
            assertEquals(20, tvShowList.size)
        }

        viewModel.getAllTvShowsList().observeForever(observer)
        verify(observer).onChanged(dummyTvShowSList)
    }
}