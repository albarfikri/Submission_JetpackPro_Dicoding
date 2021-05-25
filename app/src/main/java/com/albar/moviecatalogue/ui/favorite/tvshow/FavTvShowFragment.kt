package com.albar.moviecatalogue.ui.favorite.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.albar.moviecatalogue.R
import com.albar.moviecatalogue.databinding.FragmentFavTvshowBinding
import com.albar.moviecatalogue.ui.favorite.FavViewModel
import com.albar.moviecatalogue.ui.main.tvshow.TvShowAdapter
import com.albar.moviecatalogue.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class FavTvShowFragment : DaggerFragment() {
    private lateinit var binding: FragmentFavTvshowBinding
    private lateinit var viewModel: FavViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavTvshowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerFavTvShows()
        parentFragment?.let {
            viewModel = ViewModelProvider(it, viewModelFactory)[FavViewModel::class.java]
        }
        observerFavTvShows()
    }

    private fun observerFavTvShows() {
        viewModel.getFavTvShow().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.apply {
                    rvTvshow.adapter?.let { adapter ->
                        when (adapter) {
                            is TvShowAdapter -> {
                                if (it.isNullOrEmpty()) {
                                    favoriteTvshowEmptyState.noFav.visibility = View.VISIBLE
                                    favoriteTvshowEmptyState.textNoData.text =
                                        getText(R.string.no_fav_tvshow)
                                    rvTvshow.visibility = View.GONE
                                } else {
                                    favoriteTvshowEmptyState.noFav.visibility = View.GONE
                                    progressBar.visibility = View.GONE
                                    adapter.submitList(it)
                                    adapter.notifyDataSetChanged()
                                    rvTvshow.visibility = View.VISIBLE
                                }
                            }
                        }
                    }
                }
            }
        })
    }

    private fun recyclerFavTvShows() {
        binding.rvTvshow.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TvShowAdapter(context)
        }
    }
}