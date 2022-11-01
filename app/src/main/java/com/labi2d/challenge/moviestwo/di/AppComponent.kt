package com.labi2d.challenge.moviestwo.di

import android.app.Application
import com.labi2d.challenge.moviestwo.ui.home.HomeFragmentComponent
import com.labi2d.challenge.moviestwo.ui.home.HomeFragmentModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AppDataModule::class])
interface AppComponent {

    fun plus(module: HomeFragmentModule): HomeFragmentComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }

}