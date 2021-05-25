package com.albar.moviecatalogue.ui.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.albar.moviecatalogue.R
import com.albar.moviecatalogue.ui.favorite.movie.FavMovieFragment
import com.albar.moviecatalogue.ui.favorite.tvshow.FavTvShowFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val tabTitles = intArrayOf(R.string.movie, R.string.tvshow)
    }

    override fun getCount(): Int = tabTitles.size

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> FavMovieFragment()
            1 -> FavTvShowFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence =
        mContext.resources.getString(tabTitles[position])
}