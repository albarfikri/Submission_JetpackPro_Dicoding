package com.albar.moviecatalogue.ui.main.movie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.albar.moviecatalogue.BuildConfig
import com.albar.moviecatalogue.R
import com.albar.moviecatalogue.data.local.entity.MoviesEntity
import com.albar.moviecatalogue.databinding.ItemsMovieBinding
import com.albar.moviecatalogue.ui.detailcatalogue.CatalogueDetailActivity
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class MovieAdapter(private val context: Context) :
    PagedListAdapter<MoviesEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MoviesEntity>() {
            override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem.idMovies == newItem.idMovies && oldItem.idMovies == newItem.idMovies
            }

            override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val itemsMovieBinding =
            ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position) as MoviesEntity)
        holder.binding.tvMovieImage.startAnimation(
            AnimationUtils.loadAnimation(
                context,
                R.anim.fragment_open_enter
            )
        )
        holder.binding.tvMovieName.startAnimation(
            AnimationUtils.loadAnimation(
                context,
                R.anim.fade_in
            )
        )
    }

    inner class MovieViewHolder(val binding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MoviesEntity) {
            with(binding) {
                tvMovieName.text = movie.title
                tvMovieReview.text = movie.voteAverage.toString()
                tvMovieDate.text = movie.releaseDate
                tvMovieRating.progress = movie.voteAverage.toFloat()
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, CatalogueDetailActivity::class.java)
                    intent.putExtra(CatalogueDetailActivity.extraIdMovie, movie.idMovies)
                    intent.putExtra(CatalogueDetailActivity.type, "Movie")
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(BuildConfig.IMAGE_URL + movie.posterPath)
                    .transition(GenericTransitionOptions.with(R.anim.fragment_open_enter))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .transform(CenterCrop(), RoundedCorners(10))
                    .into(tvMovieImage)
            }
        }
    }
}