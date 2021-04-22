package com.albar.moviecatalogue.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.albar.moviecatalogue.R
import com.albar.moviecatalogue.databinding.FragmentTvShowBinding


class TvShowFragment : Fragment() {
    private lateinit var binding: FragmentTvShowBinding

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
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[TvShowViewModel::class.java]

            val tvShow = viewModel.getAllTvShowDummy()
            val tvShowAdapter = this.context?.let { TvShowAdapter(it) }
            tvShowAdapter?.setTvShow(tvShow)

            with(binding.rvTvshow) {
                layoutManager = LinearLayoutManager(context)
                val lac = LayoutAnimationController(
                    AnimationUtils.loadAnimation(
                        context,
                        R.anim.item_anim
                    )
                )
                lac.delay = 0.20f
                lac.order = LayoutAnimationController.ORDER_NORMAL
                layoutAnimation = lac
                setHasFixedSize(true)
                this.adapter = tvShowAdapter
                startLayoutAnimation()
            }
        }
    }
}