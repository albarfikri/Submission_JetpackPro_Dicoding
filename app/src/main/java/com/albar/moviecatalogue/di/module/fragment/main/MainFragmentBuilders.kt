package com.albar.moviecatalogue.di.module.fragment.main

import com.albar.moviecatalogue.ui.main.movie.MovieFragment
import com.albar.moviecatalogue.ui.main.tvshow.TvShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuilders {
    @ContributesAndroidInjector
    abstract fun mainMovieFragment(): MovieFragment

    @ContributesAndroidInjector
    abstract fun mainTvShowFragment(): TvShowFragment
}