package com.albar.moviecatalogue.ui.detailcatalogue

import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.albar.moviecatalogue.BuildConfig
import com.albar.moviecatalogue.R
import com.albar.moviecatalogue.data.CatalogueDataModel
import com.albar.moviecatalogue.data.CatalogueEntity
import com.albar.moviecatalogue.databinding.DetailCatalogueBinding
import com.albar.moviecatalogue.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class CatalogueDetailActivity : AppCompatActivity() {

    private lateinit var detailCatalogue: DetailCatalogueBinding

    companion object {
        const val extraIdMovie = "extra_Id_Movie"
        const val extraIdTvShow = "extra_Id_TvShow"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailCatalogue = DetailCatalogueBinding.inflate(layoutInflater)
        setContentView(detailCatalogue.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(
            this@CatalogueDetailActivity,
            factory
        )[CatalogueDetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val catalogueIdMovie = extras.getInt(extraIdMovie, 0)
            val catalogueIdTvShow = extras.getInt(extraIdTvShow, 0)
            Log.d("testing", catalogueIdMovie.toString())

            viewModel.getMovieDetailById(catalogueIdMovie).observe(this, Observer {
                it?.let {
                    populateData(it)
                }
            })
//            if (catalogueIdTvShow != null) {
//                viewModel.getMovieDetailById(catalogueIdMovie).observe(this, Observer{
//                    populateData(it)
//                })
//            }
        }
    }

    private fun populateData(data: CatalogueDataModel) {
        detailCatalogue.apply {
            tvRelease.text = data.releaseDate
            tvMovieRating.progress = data.voteAverage?.toFloat()!!
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
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .transform(CenterCrop(), RoundedCorners(20))
                .into(tvBackdrop)
            collapsingToolbar.title = data.title
            collapsingToolbar.setExpandedTitleTextColor(getColorStateList(R.color.white))

        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}