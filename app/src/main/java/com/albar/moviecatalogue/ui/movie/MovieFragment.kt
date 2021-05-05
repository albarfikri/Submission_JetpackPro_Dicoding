package com.albar.moviecatalogue.ui.movie

import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.albar.moviecatalogue.data.source.CatalogueRepository
import com.albar.moviecatalogue.databinding.FragmentMovieBinding
import com.albar.moviecatalogue.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding
    private lateinit var viewModel: MovieViewModel

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
            val viewModelFactory = ViewModelFactory.getInstance()
            activity?.let {
                viewModel = ViewModelProvider(
                    it,
                    viewModelFactory
                )[MovieViewModel::class.java]
            }

            viewModel()
            recyclerMovies()
        }
    }

    private fun viewModel() {
        viewModel.getLoadingState().observe(this, {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.getAllMoviesList().observe(viewLifecycleOwner, Observer { listMovie ->
            binding.rvMovie.adapter?.let { adapter ->
                when (adapter) {
                    is MovieAdapter -> adapter.setMovie(listMovie)
                }
            }
        })
    }

    private fun recyclerMovies() {
        with(binding.rvMovie) {
            val orientation = resources.configuration.orientation
            if (orientation == SCREEN_ORIENTATION_PORTRAIT) {
                binding.rvMovie.layoutManager = GridLayoutManager(context, 2)
            } else {
                binding.rvMovie.layoutManager = GridLayoutManager(context, 3)
            }
            setHasFixedSize(true)
            adapter = MovieAdapter(context)
        }
    }

}