package com.albar.moviecatalogue.ui.movie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.albar.moviecatalogue.BuildConfig
import com.albar.moviecatalogue.R
import com.albar.moviecatalogue.data.source.remote.response.ResultsItemMovie
import com.albar.moviecatalogue.databinding.ItemsMovieBinding
import com.albar.moviecatalogue.ui.detailcatalogue.CatalogueDetailActivity
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class MovieAdapter(private val context: Context) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private val listMovie = ArrayList<ResultsItemMovie>()

    fun setMovie(movie: List<ResultsItemMovie>) {
        this.listMovie.clear()
        this.listMovie.addAll(movie)
        notifyDataSetChanged()
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
        val movie = listMovie[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovie.size

    class MovieViewHolder(val binding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: ResultsItemMovie) {
            with(binding) {
                tvMovieName.text = movie.title
                tvMovieReview.text = movie.voteAverage.toString()
                tvMovieDate.text = movie.releaseDate
                tvMovieRating.progress = movie.voteAverage.toFloat()

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, CatalogueDetailActivity::class.java)
                    intent.putExtra(CatalogueDetailActivity.extraIdMovie, movie.id)
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