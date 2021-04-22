package com.albar.moviecatalogue.ui.tvshow

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.albar.moviecatalogue.R
import com.albar.moviecatalogue.data.CatalogueEntity
import com.albar.moviecatalogue.databinding.ItemsTvshowBinding
import com.albar.moviecatalogue.ui.detailcatalogue.CatalogueDetailActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class TvShowAdapter(private val context: Context) :
    RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    private val listTvShow = ArrayList<CatalogueEntity>()

    fun setTvShow(tvShow: List<CatalogueEntity>) {
        this.listTvShow.clear()
        this.listTvShow.addAll(tvShow)
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
        val tvShow = listTvShow[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listTvShow.size

    class TvShowViewHolder(val binding: ItemsTvshowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: CatalogueEntity) {
            with(binding) {
                tvShowName.text = tvShow.movieName
                tvShowPrice.text = tvShow.price
                tvShowReview.text = tvShow.review
                tvShowDuration.text = tvShow.duration
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, CatalogueDetailActivity::class.java)
                    intent.putExtra(CatalogueDetailActivity.extraIdTvShow, tvShow.id)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(tvShow.image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )

                    .into(tvShowImage)
            }
        }
    }
}