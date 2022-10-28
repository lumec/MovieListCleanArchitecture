package com.labi2d.challenge.moviestwo.model

import com.labi2d.challenge.moviestwo.R
import com.labi2d.challenge.moviestwo.model.datasource.FilmLocalDataSource
import com.labi2d.challenge.moviestwo.model.datasource.FilmRemoteDataSource
import com.labi2d.challenge.moviestwo.ui.App
import com.labi2d.challenge.moviestwo.model.database.Film
import kotlinx.coroutines.flow.Flow

class FilmsRepository(application: App) {

    private val localDataSource = FilmLocalDataSource(application.db.filmDao())
    private val remoteDataSource = FilmRemoteDataSource(application.getString(R.string.api_key))

    val films = localDataSource.films

    fun findByType(type: String): Flow<List<Film>> = localDataSource.findByType(type)

    suspend fun requestFilms() {
        if (localDataSource.isEmpty()) {
            val films = remoteDataSource.fetchFilms()
            localDataSource.save(films.results.toLocalModel())
        }
    }
}

private fun List<RemoteFilm>.toLocalModel(): List<Film> = map { it.toLocalModel() }

private fun RemoteFilm.toLocalModel(): Film = Film(
    id = 0,
    name ?: "",
    type?: ""
)