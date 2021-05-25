package com.albar.moviecatalogue.ui.detailcatalogue

import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.albar.moviecatalogue.BuildConfig
import com.albar.moviecatalogue.R
import com.albar.moviecatalogue.data.local.entity.MoviesEntity
import com.albar.moviecatalogue.data.local.entity.TvShowsEntity
import com.albar.moviecatalogue.databinding.DetailCatalogueBinding
import com.albar.moviecatalogue.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class CatalogueDetailActivity : DaggerAppCompatActivity() {

    private lateinit var detailCatalogue: DetailCatalogueBinding

    companion object {
        const val extraIdMovie = "extra_Id_Movie"
        const val extraIdTvShow = "extra_Id_TvShow"
        const val type = "not set"
    }

    private lateinit var viewModel: CatalogueDetailViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailCatalogue = DetailCatalogueBinding.inflate(layoutInflater)
        setContentView(detailCatalogue.root)

        setupBackButton()

        viewModel = ViewModelProvider(
            this@CatalogueDetailActivity,
            viewModelFactory
        )[CatalogueDetailViewModel::class.java]
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel()
    }

    private fun setupBackButton() {
        setSupportActionBar(detailCatalogue.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        detailCatalogue.collapsingToolbar.setExpandedTitleColor(Color.TRANSPARENT)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun observeDetailMovie(movieId: Int) {
        viewModel.getMovieDetailById(movieId).observe(this, Observer {
            populateDataMovie(it)
        })
    }

    private fun observeDetailTvShow(tvShowId: Int) {
        viewModel.getTvShowDetailById(tvShowId).observe(this, Observer {
            populateDataTvShow(it)
        })
    }

    private fun setFavoriteCatalogue(movie: MoviesEntity?, tvShow: TvShowsEntity?) {
        if (movie != null) {
            if (movie.isFavorited) {
                Snackbar.make(
                    window.decorView.rootView,
                    "Favorite user is removed !",
                    Snackbar.LENGTH_SHORT
                ).show()

            } else {
                Snackbar.make(
                    window.decorView.rootView,
                    "Favorite user is saved !",
                    Snackbar.LENGTH_SHORT
                ).show()

            }
            viewModel.setFavMovie(movie)
        } else {
            if (tvShow != null) {
                if (tvShow.isFavorited) {
                    Snackbar.make(
                        window.decorView.rootView,
                        "Favorite user is removed !",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    Snackbar.make(
                        window.decorView.rootView,
                        "Favorite user is saved !",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                viewModel.setFavTvShow(tvShow)
            }
        }
    }

    private fun setFavState(status: Boolean) {
        if (status) {
            detailCatalogue.btnFavorite.setImageResource(R.drawable.ic_baseline_bookmark_24_saved)
        } else {
            detailCatalogue.btnFavorite.setImageResource(R.drawable.ic_baseline_bookmark_24)
        }
    }

    private fun viewModel() {
        val extras = intent.extras
        if (extras != null) {
            val catalogueIdMovie = extras.getInt(extraIdMovie, 0)
            val catalogueIdTvShow = extras.getInt(extraIdTvShow, 0)
            val type = extras.getString(type)

            if (type.equals("Movie")) {
                observeDetailMovie(catalogueIdMovie)

            } else if (type.equals("TvShow")) {
                observeDetailTvShow(catalogueIdTvShow)
            }
        }
    }

    private fun populateDataMovie(data: MoviesEntity) {
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
                .transform(CenterCrop(), RoundedCorners(50))
                .into(tvImage)

            Glide.with(this@CatalogueDetailActivity)
                .load(BuildConfig.IMAGE_URL + data.backDropPath)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(tvBackdrop)
            collapsingToolbar.title = data.title
            collapsingToolbar.setExpandedTitleTextColor(getColorStateList(R.color.white))

            val isFavorited = data.isFavorited
            setFavState(isFavorited)
        }

        detailCatalogue.btnFavorite.setOnClickListener {
            setFavoriteCatalogue(data, null)
        }
    }

    private fun populateDataTvShow(data: TvShowsEntity) {
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
                .load(BuildConfig.IMAGE_URL + data.backDropPath)
                .transform(CenterCrop(), RoundedCorners(20))
                .into(tvBackdrop)
            collapsingToolbar.title = data.originalName
            collapsingToolbar.setExpandedTitleTextColor(getColorStateList(R.color.white))
        }
        val isFavorited = data.isFavorited
        setFavState(isFavorited)
        detailCatalogue.btnFavorite.setOnClickListener {
            setFavoriteCatalogue(null, data)
        }
    }
}
