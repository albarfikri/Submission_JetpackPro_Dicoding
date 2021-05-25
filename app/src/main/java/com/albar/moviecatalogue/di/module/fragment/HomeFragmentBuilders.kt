package com.albar.moviecatalogue.di.module.fragment

import com.albar.moviecatalogue.di.module.fragment.fav.FavFragmentBuilders
import com.albar.moviecatalogue.di.module.fragment.main.MainFragmentBuilders
import com.albar.moviecatalogue.ui.favorite.FavFragment
import com.albar.moviecatalogue.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBuilders {
    @ContributesAndroidInjector(modules = [MainFragmentBuilders::class])
    abstract fun mainCatalogue(): MainFragment

    @ContributesAndroidInjector(modules = [FavFragmentBuilders::class])
    abstract fun favDi(): FavFragment
}