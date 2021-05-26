package com.albar.moviecatalogue.ui.main.tvshow

import android.annotation.SuppressLint
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
import com.albar.moviecatalogue.data.local.entity.TvShowsEntity
import com.albar.moviecatalogue.databinding.ItemsTvshowBinding
import com.albar.moviecatalogue.ui.detailcatalogue.CatalogueDetailActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class TvShowAdapter(private val context: Context) :
    PagedListAdapter<TvShowsEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowsEntity>() {
            override fun areItemsTheSame(oldItem: TvShowsEntity, newItem: TvShowsEntity): Boolean {
                return oldItem.idTvShow == newItem.idTvShow
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: TvShowsEntity,
                newItem: TvShowsEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvShowViewHolder {
        val itemsTvshowBinding =
            ItemsTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemsTvshowBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(getItem(position) as TvShowsEntity)
        holder.binding.tvShowImage.startAnimation(
            AnimationUtils.loadAnimation(
                context,
                R.anim.fade_transition_animation
            )
        )
        holder.itemView.startAnimation(
            AnimationUtils.loadAnimation(
                context,
                R.anim.fade_scale_animation
            )
        )

    }

    class TvShowViewHolder(val binding: ItemsTvshowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowsEntity) {
            with(binding) {
                tvShowName.text = tvShow.originalName
                tvShowDate.text = tvShow.firstAirDate
                tvShowReview.text = tvShow.voteAverage.toString()
                tvShowDuration.text = tvShow.voteAverage.toString()
                tvShowImageView.progress = tvShow.voteAverage.toFloat()
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, CatalogueDetailActivity::class.java)
                    intent.putExtra(CatalogueDetailActivity.extraIdTvShow, tvShow.idTvShow)
                    intent.putExtra(CatalogueDetailActivity.type, "TvShow")
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(BuildConfig.IMAGE_URL + tvShow.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(tvShowImage)
            }
        }
    }
}