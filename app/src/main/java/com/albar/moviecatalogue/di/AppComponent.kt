package com.albar.moviecatalogue.di

import android.app.Application
import com.albar.moviecatalogue.di.module.CatalogueApp
import com.albar.moviecatalogue.di.module.Network
import com.albar.moviecatalogue.di.module.activity.ActivityBuilders
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilders::class,
        CatalogueApp::class,
        Network::class]
)

interface AppComponent : AndroidInjector<BaseCatalogueApp> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
