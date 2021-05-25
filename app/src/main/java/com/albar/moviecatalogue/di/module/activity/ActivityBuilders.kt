package com.albar.moviecatalogue.di.module.activity

import com.albar.moviecatalogue.MainActivity
import com.albar.moviecatalogue.di.module.fragment.HomeFragmentBuilders
import com.albar.moviecatalogue.ui.detailcatalogue.CatalogueDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilders {
    @ContributesAndroidInjector(modules = [HomeFragmentBuilders::class])
    abstract fun homeActivityDi(): MainActivity

    @ContributesAndroidInjector
    abstract fun detailActivityDi(): CatalogueDetailActivity
}