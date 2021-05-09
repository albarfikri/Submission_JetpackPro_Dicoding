package com.albar.moviecatalogue.ui.detailcatalogue

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.albar.moviecatalogue.BuildConfig
import com.albar.moviecatalogue.R
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemMovie
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemTvShow
import com.albar.moviecatalogue.databinding.DetailCatalogueBinding
import com.albar.moviecatalogue.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar

class CatalogueDetailActivity : AppCompatActivity() {

    private lateinit var detailCatalogue: DetailCatalogueBinding

    companion object {
        const val extraIdMovie = "extra_Id_Movie"
        const val extraIdTvShow = "extra_Id_TvShow"
        const val type = "not set"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailCatalogue = DetailCatalogueBinding.inflate(layoutInflater)
        setContentView(detailCatalogue.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel()
        buttonFavoriteChecked()
    }

    private fun buttonFavoriteChecked() {
        var isFavChecked = true
        detailCatalogue.apply {
            btnFavorite.setOnClickListener {
                isFavChecked = !isFavChecked
                if (isFavChecked) {
                    btnFavorite.setImageResource(R.drawable.ic_baseline_bookmark_24)
                    Snackbar.make(
                        window.decorView.rootView,
                        "Favorite user is removed !",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    btnFavorite.setImageResource(R.drawable.ic_baseline_bookmark_24_saved)

                    Snackbar.make(
                        window.decorView.rootView,
                        "Favorite user is saved !",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun viewModel() {
        val factory = ViewModelFactory.getInstance()

        val viewModel = ViewModelProvider(
            this@CatalogueDetailActivity,
            factory
        )[CatalogueDetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val catalogueIdMovie = extras.getInt(extraIdMovie, 0)
            val catalogueIdTvShow = extras.getInt(extraIdTvShow, 0)
            val type = extras.getString(type)

            if (type.equals("Movie")) {
                viewModel.getLoadingState().observe(this, {
                    detailCatalogue.progressBar.visibility = if (it) View.VISIBLE else View.GONE
                })
                viewModel.getMovieDetailById(catalogueIdMovie).observe(this, {
                    it?.let {
                        populateDataMovie(it)
                    }
                })
            } else if (type.equals("TvShow")) {
                viewModel.getTvShowDetailById(catalogueIdTvShow).observe(this, {
                    it?.let {
                        populateDataTvShow(it)
                    }
                })
            }
        }
    }

    private fun populateDataMovie(data: ResultsItemMovie) {
        detailCatalogue.apply {
            tvRelease.text = data.releaseDate
            tvMovieRatingCircle.progress = data.voteAverage.toFloat()
            tvMovieRatingText.text = data.voteAverage.toString()
            tvAbout.text = data.overview
            Glide.with(this@CatalogueDetailActivity)
                .load(BuildConfig.IMAGE_URL + data.posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .transform(CenterCrop(), RoundedCorners(20))
                .into(tvImage)

            Glide.with(this@CatalogueDetailActivity)
                .load(BuildConfig.IMAGE_URL + data.backdropPath)
                .transform(CenterCrop(), RoundedCorners(20))
                .into(tvBackdrop)
            collapsingToolbar.title = data.title
            collapsingToolbar.setExpandedTitleTextColor(getColorStateList(R.color.white))

        }
    }

    private fun populateDataTvShow(data: ResultsItemTvShow) {
        detailCatalogue.apply {
            tvRelease.text = data.firstAirDate
            tvMovieRatingCircle.progress = data.voteAverage.toFloat()
            tvMovieRatingText.text = data.voteAverage.toString()
            tvAbout.text = data.overview
            Glide.with(this@CatalogueDetailActivity)
                .load(BuildConfig.IMAGE_URL + data.posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .transform(CenterCrop(), RoundedCorners(20))
                .into(tvImage)

            Glide.with(this@CatalogueDetailActivity)
                .load(BuildConfig.IMAGE_URL + data.backdropPath)
                .transform(CenterCrop(), RoundedCorners(20))
                .into(tvBackdrop)
            collapsingToolbar.title = data.originalName
            collapsingToolbar.setExpandedTitleTextColor(getColorStateList(R.color.white))

        }
    }
}