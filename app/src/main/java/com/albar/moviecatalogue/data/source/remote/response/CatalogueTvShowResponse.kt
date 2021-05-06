package com.albar.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CatalogueTvShowResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<ResultsItemTvShow>,

	@field:SerializedName("total_results")
	val totalResults: Int
)

data class ResultsItemTvShow(
	@field:SerializedName("id")
	val id: Int = 0,

	@field:SerializedName("original_name")
	val originalName: String? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@field:SerializedName("first_air_date")
	val firstAirDate: String? = null,

	@field:SerializedName("popularity")
	val popularity: Double = 0.0,

	@field:SerializedName("vote_average")
	val voteAverage: Double = 0.0,

	@field:SerializedName("vote_count")
	val voteCount: Int = 0,

	@field:SerializedName("overview")
	val overview: String? = null
)
