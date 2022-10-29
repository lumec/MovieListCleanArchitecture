package com.labi2d.challenge.moviestwo.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {

    @Query("SELECT * FROM Film")
    fun getAll() : Flow<List<Film>>

    @Query("SELECT * FROM Film WHERE type = :type")
    fun findByType(type: String) : Flow<List<Film>>

    @Query("SELECT COUNT(id) FROM Film")
    suspend fun countFilm(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilms(films: List<Film>)
}