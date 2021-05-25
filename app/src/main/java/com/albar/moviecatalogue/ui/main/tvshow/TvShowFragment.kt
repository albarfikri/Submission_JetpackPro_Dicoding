package com.albar.moviecatalogue.ui.main.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.albar.moviecatalogue.databinding.FragmentMovieBinding
import com.albar.moviecatalogue.databinding.FragmentTvShowBinding
import com.albar.moviecatalogue.ui.main.movie.MovieAdapter
import com.albar.moviecatalogue.ui.main.movie.MovieViewModel
import com.albar.moviecatalogue.viewmodel.ViewModelFactory
import com.albar.moviecatalogue.vo.Status
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class TvShowFragment : DaggerFragment() {
    private lateinit var binding: FragmentTvShowBinding
    private lateinit var viewModel: TvShowViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recylerTvShows()
        activity?.let{
            viewModelSet(it)
        }

        parentFragment?.let {
            viewModel = ViewModelProvider(it, viewModelFactory)[TvShowViewModel::class.java]
        }
        observerTvShows()
    }

    private fun observerTvShows() {
        viewModel.getAllTvShowList().observe(viewLifecycleOwner, { listTvShow ->
            if (listTvShow!= null) {
                when (listTvShow.status) {
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rvTvshow.adapter?.let { adapter ->
                            when (adapter) {
                                is TvShowAdapter -> {
                                    adapter.submitList(listTvShow.data)
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

    private fun viewModelSet(fragment: FragmentActivity) {
        viewModel = ViewModelProvider(fragment, viewModelFactory)[TvShowViewModel::class.java]
    }

    private fun recylerTvShows() {
        binding.rvTvshow.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = TvShowAdapter(context)
        }
    }
}
