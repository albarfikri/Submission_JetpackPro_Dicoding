package com.albar.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemMovie
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemTvShow

interface CatalogueDataSource {

    fun getMovie(): LiveData<List<ResultsItemMovie>>

    fun getMovieById(movieId: Int): LiveData<ResultsItemMovie>

    fun getTvShow(): LiveData<List<ResultsItemTvShow>>

    fun getTvShowById(tvShowId: Int): LiveData<ResultsItemTvShow>
}