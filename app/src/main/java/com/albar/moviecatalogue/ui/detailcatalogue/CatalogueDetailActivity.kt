package com.albar.moviecatalogue.ui.detailcatalogue

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.albar.moviecatalogue.R
import com.albar.moviecatalogue.data.CatalogueEntity
import com.albar.moviecatalogue.databinding.ActivityCatalogueDetailBinding
import com.albar.moviecatalogue.databinding.ContentDetailCatalogueBinding
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class CatalogueDetailActivity : AppCompatActivity() {

    private lateinit var detailContentBinding: ContentDetailCatalogueBinding

    companion object {
        const val extraIdMovie = "extra_Id_Movie"
        const val extraIdTvShow = "extra_Id_TvShow"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityBinding: ActivityCatalogueDetailBinding =
            ActivityCatalogueDetailBinding.inflate(layoutInflater)
        detailContentBinding = activityBinding.detailContent
        setContentView(activityBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[CatalogueDetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val catalogueIdMovie = extras.getString(extraIdMovie)
            val catalogueIdTvShow = extras.getString(extraIdTvShow)
            if (catalogueIdMovie != null) {
                viewModel.getMovieById(catalogueIdMovie)
                populateCatalogue(viewModel.getAllMovie())
            }
            if (catalogueIdTvShow != null) {
                viewModel.getMovieById(catalogueIdTvShow)
                populateCatalogue(viewModel.getAllTvShow())
            }
        }
    }


    private fun populateCatalogue(catalogueEntity: CatalogueEntity) {
        detailContentBinding.apply {
            tvName.text = catalogueEntity.movieName
            tvRelease.text = catalogueEntity.release.toString()
            tvPrice.text = catalogueEntity.price
            tvReview.text = catalogueEntity.review
            tvRating.text = catalogueEntity.rating.toString()
            tvDuration.text = catalogueEntity.duration
            tvAbout.text = catalogueEntity.about
            Glide.with(this@CatalogueDetailActivity)
                .load(catalogueEntity.image)
                .transition(GenericTransitionOptions.with(R.anim.fragment_open_enter))
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .transform(CenterCrop(), RoundedCorners(20))
                .into(image)

            Glide.with(this@CatalogueDetailActivity)
                .load(catalogueEntity.imageBackground)
                .transition(GenericTransitionOptions.with(R.anim.fragment_open_enter))
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .transform(RoundedCorners(40))
                .into(imagePoster)
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