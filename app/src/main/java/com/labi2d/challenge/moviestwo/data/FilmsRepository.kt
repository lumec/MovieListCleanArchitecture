package com.labi2d.challenge.moviestwo.data

import com.labi2d.challenge.moviestwo.R
import com.labi2d.challenge.moviestwo.framework.datasource.FilmRoomDataSource
import com.labi2d.challenge.moviestwo.framework.datasource.FilmServerDataSource
import com.labi2d.challenge.moviestwo.App
import com.labi2d.challenge.moviestwo.domain.Film
import kotlinx.coroutines.flow.Flow

class FilmsRepository(application: App) {

    private val localDataSource = FilmRoomDataSource(application.db.filmDao())
    private val remoteDataSource = FilmServerDataSource(application.getString(R.string.api_key))

    val films = localDataSource.films

    fun findByType(type: String): Flow<List<Film>> = localDataSource.findByType(type)

    suspend fun requestFilms(): Error? = tryCall {
        if (localDataSource.isEmpty()) {
            val films = remoteDataSource.fetchFilms()
            localDataSource.save(films)
        }
    }
}