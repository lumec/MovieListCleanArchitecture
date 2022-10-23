package com.labi2d.challenge.moviestwo.ui

import android.app.Application
import androidx.room.Room
import com.labi2d.challenge.moviestwo.model.database.FilmDatabase

class App : Application() {

    lateinit var db: FilmDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            this,
            FilmDatabase::class.java, "film-database"
        ).build()
    }
}