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
	val id: Int,

	@field:SerializedName("original_name")
	val originalName: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("first_air_date")
	val firstAirDate: String,

	@field:SerializedName("popularity")
	val popularity: Double,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("vote_count")
	val voteCount: Int,

	@field:SerializedName("overview")
	val overview: String
)
