package com.labi2d.challenge.moviestwo

import android.app.Application
import com.labi2d.challenge.moviestwo.di.AppComponent
import com.labi2d.challenge.moviestwo.di.DaggerAppComponent

class App : Application() {

    lateinit var component: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent
            .factory()
            .create(this)
    }
}