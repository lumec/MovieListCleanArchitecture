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

    @Query("SELECT id, name, type \n" +
            "FROM\n" +
            "    (\n" +
            "        SELECT id, name, type,\n" +
            "        (CASE WHEN TYPE = :type THEN type else \"ALL\" END ) AS category\n" +
            "        FROM Film WHERE category = :type\n" +
            "    ) AS TEMP_FILM")
    fun findByType(type: String) : Flow<List<Film>>

    @Query("SELECT COUNT(id) FROM Film")
    suspend fun countFilm(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilms(films: List<Film>)
}