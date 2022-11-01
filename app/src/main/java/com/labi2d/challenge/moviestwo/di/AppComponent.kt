package com.labi2d.challenge.moviestwo.di

import android.app.Application
import com.labi2d.challenge.moviestwo.ui.home.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AppDataModule::class])
interface AppComponent {

    fun inject(fragment: HomeFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }

}