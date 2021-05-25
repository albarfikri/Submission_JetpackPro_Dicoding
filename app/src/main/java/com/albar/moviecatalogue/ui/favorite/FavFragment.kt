package com.albar.moviecatalogue.ui.favorite

import android.app.ActionBar
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.albar.moviecatalogue.R
import com.albar.moviecatalogue.databinding.FragmentFavoriteBinding
import com.albar.moviecatalogue.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FavFragment : DaggerFragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel: FavViewModel

    @Inject
    lateinit var factoryViewModel: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let { viewPagerSetup(it) }
        viewModel = ViewModelProvider(this@FavFragment, factoryViewModel)[FavViewModel::class.java]
    }

    private fun viewPagerSetup(context: Context) {
        val sectionsPagerAdapter = SectionsPagerAdapter(context, childFragmentManager)
        binding.apply {
            viewPager.adapter = sectionsPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }
    }
}