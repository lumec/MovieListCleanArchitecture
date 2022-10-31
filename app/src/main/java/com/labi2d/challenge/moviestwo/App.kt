package com.labi2d.challenge.moviestwo

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDi()
    }
}