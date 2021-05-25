package com.albar.moviecatalogue.ui.favorite.movie

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.albar.moviecatalogue.R
import com.albar.moviecatalogue.databinding.FragmentFavMovieBinding
import com.albar.moviecatalogue.ui.favorite.FavViewModel
import com.albar.moviecatalogue.ui.main.movie.MovieAdapter
import com.albar.moviecatalogue.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FavMovieFragment : DaggerFragment() {
    private lateinit var binding: FragmentFavMovieBinding
    private lateinit var viewModel: FavViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recylerFavMovies()
        parentFragment?.let {
            viewModel = ViewModelProvider(it, factory)[FavViewModel::class.java]
        }
        observerFavMovies()
    }

    private fun recylerFavMovies() {
        with(binding.rvMovie) {
            val orientation = resources.configuration.orientation
            if (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                binding.rvMovie.layoutManager = GridLayoutManager(context, 2)
            } else {
                binding.rvMovie.layoutManager = GridLayoutManager(context, 3)
            }
            setHasFixedSize(true)
            adapter = MovieAdapter(context)
        }
    }

    private fun observerFavMovies() {
        viewModel.getFavMovies().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.apply {
                    rvMovie.adapter?.let { adapter ->
                        when (adapter) {
                            is MovieAdapter -> {
                                if (it.isNullOrEmpty()) {
                                    favoriteMovieEmptyState.noFav.visibility = VISIBLE
                                    favoriteMovieEmptyState.textNoData.text =
                                        getText(R.string.no_fav_movie)
                                    rvMovie.visibility = GONE
                                } else {
                                    favoriteMovieEmptyState.noFav.visibility = GONE
                                    progressBar.visibility = GONE
                                    adapter.submitList(it)
                                    adapter.notifyDataSetChanged()
                                    rvMovie.visibility = VISIBLE
                                }
                            }
                        }
                    }
                }
            }
        })
    }
}