package com.labi2d.challenge.moviestwo.model.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface FilmDao {

    @Query("SELECT * FROM Film WHERE type = :type")
    fun findByType(type: String) : List<Film>
}