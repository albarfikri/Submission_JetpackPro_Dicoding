package com.albar.moviecatalogue.ui.movie

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getMovie() {
        val movieEntities = viewModel.getAllMovieDummy()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities.size)
    }
}