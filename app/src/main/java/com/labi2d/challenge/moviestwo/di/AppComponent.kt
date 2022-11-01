package com.labi2d.challenge.moviestwo.di

import android.app.Application
import com.labi2d.challenge.moviestwo.ui.home.HomeViewModelFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, UseCaseModule::class, ViewModelModule::class])
interface AppComponent {

    val homeViewModelFactory: HomeViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }

}