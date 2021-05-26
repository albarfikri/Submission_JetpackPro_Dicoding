package com.albar.moviecatalogue.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.albar.moviecatalogue.databinding.FragmentMainBinding
import com.albar.moviecatalogue.ui.main.movie.MovieViewModel
import com.albar.moviecatalogue.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MainFragment : DaggerFragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MovieViewModel

    @Inject
    lateinit var factoryViewModel: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let { viewPagerSetup(it) }
        viewModel =
            ViewModelProvider(this@MainFragment, factoryViewModel)[MovieViewModel::class.java]
    }

    private fun viewPagerSetup(context: Context) {
        val sectionsPagerAdapter = SectionsPagerAdapterMain(context, childFragmentManager)
        binding.apply {
            viewPagerCat.adapter = sectionsPagerAdapter
            tabsCat.setupWithViewPager(viewPagerCat)
        }
    }
}