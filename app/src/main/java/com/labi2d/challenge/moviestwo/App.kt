package com.labi2d.challenge.moviestwo

import android.app.Application
import androidx.room.Room
import com.labi2d.challenge.moviestwo.framework.database.FilmDatabase

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