package com.albar.moviecatalogue.ui.movie

import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.albar.moviecatalogue.databinding.FragmentMovieBinding

class MovieFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[MovieViewModel::class.java]

            val movie = viewModel.getAllMovieDummy()
            val movieAdapter = context?.let { MovieAdapter(it) }
            movieAdapter?.setMovie(movie)

            with(binding.rvMovie) {
                val orientation = resources.configuration.orientation
                if (orientation == SCREEN_ORIENTATION_PORTRAIT) {
                    binding.rvMovie.layoutManager = GridLayoutManager(context, 2)
                } else {
                    binding.rvMovie.layoutManager = GridLayoutManager(context, 3)
                }
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }
}