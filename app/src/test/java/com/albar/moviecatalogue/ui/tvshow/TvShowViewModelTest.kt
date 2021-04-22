package com.albar.moviecatalogue.ui.tvshow

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShow(){
        val tvShowEntities = viewModel.getAllTvShowDummy()
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities.size)
    }
}