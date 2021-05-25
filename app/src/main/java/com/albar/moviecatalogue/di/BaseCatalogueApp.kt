package com.albar.moviecatalogue.di

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseCatalogueApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()
}