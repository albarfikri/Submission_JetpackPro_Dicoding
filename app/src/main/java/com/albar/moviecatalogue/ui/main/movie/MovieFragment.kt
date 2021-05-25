package com.albar.moviecatalogue.ui.main.movie

import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.albar.moviecatalogue.databinding.FragmentMovieBinding
import com.albar.moviecatalogue.viewmodel.ViewModelFactory
import com.albar.moviecatalogue.vo.Status
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class MovieFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

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
        recyclerMovies()

        activity?.let {
            viewModelSet(it)
        }
        parentFragment?.let {
            viewModel = ViewModelProvider(it, viewModelFactory)[MovieViewModel::class.java]
        }

        observerMovies()
    }

    private fun viewModelSet(fragment: FragmentActivity) {
        viewModel = ViewModelProvider(fragment, viewModelFactory)[MovieViewModel::class.java]
    }

    private fun recyclerMovies() {
        with(binding.rvMovie) {
            val orientation = resources.configuration.orientation
            if (orientation == SCREEN_ORIENTATION_PORTRAIT) {
                binding.rvMovie.layoutManager = GridLayoutManager(context, 2)
            } else {
                binding.rvMovie.layoutManager = GridLayoutManager(context, 3)
            }
            adapter = MovieAdapter(context)
        }
    }

    private fun observerMovies() {
        viewModel.getAllMoviesList().observe(viewLifecycleOwner, Observer { listMovie ->
            if (listMovie != null) {
                when (listMovie.status) {
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rvMovie.adapter?.let { adapter ->
                            when (adapter) {
                                is MovieAdapter -> {
                                    adapter.submitList(listMovie.data)
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Internet Connection is lost", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        })
    }
}