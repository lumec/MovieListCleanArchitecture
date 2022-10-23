package com.labi2d.challenge.moviestwo.model.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Film::class], version = 1)
abstract class FilmDatabase : RoomDatabase() {

    abstract fun filmDao(): FilmDao
}