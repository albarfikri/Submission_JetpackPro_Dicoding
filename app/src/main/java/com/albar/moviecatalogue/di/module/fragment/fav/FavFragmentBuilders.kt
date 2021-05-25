package com.albar.moviecatalogue.di.module.fragment.fav

import com.albar.moviecatalogue.ui.favorite.movie.FavMovieFragment
import com.albar.moviecatalogue.ui.favorite.tvshow.FavTvShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FavFragmentBuilders {
    @ContributesAndroidInjector
    abstract fun favMovieFragment(): FavMovieFragment

    @ContributesAndroidInjector
    abstract fun favTvShowFragment(): FavTvShowFragment
}