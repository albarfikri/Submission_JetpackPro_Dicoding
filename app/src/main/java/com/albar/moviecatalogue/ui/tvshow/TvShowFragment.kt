package com.albar.moviecatalogue.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.albar.moviecatalogue.databinding.FragmentTvShowBinding
import com.albar.moviecatalogue.ui.movie.MovieFragment
import com.albar.moviecatalogue.viewmodel.ViewModelFactory


class TvShowFragment : Fragment() {
    private lateinit var binding: FragmentTvShowBinding
    private lateinit var viewModel: TvShowViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
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
                )[TvShowViewModel::class.java]

                viewModel()
                recyclerTvShows()
            }
        }
    }

    private fun viewModel() {
        MovieFragment.handler.postDelayed({
            viewModel.getLoadingState().observe(this, {
                binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            })

            viewModel.getAllTvShowsList().observe(viewLifecycleOwner, { listTvShows ->
                binding.rvTvshow.adapter?.let { adapter ->
                    when (adapter) {
                        is TvShowAdapter -> adapter.setTvShow(listTvShows)
                    }
                }
            })
        }, MovieFragment.delayedTime)
    }

    private fun recyclerTvShows() {
        with(binding.rvTvshow) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = TvShowAdapter(context)
        }
    }
}
