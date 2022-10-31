package com.labi2d.challenge.moviestwo.data

import com.labi2d.challenge.moviestwo.data.datasource.FilmLocalDataSource
import com.labi2d.challenge.moviestwo.data.datasource.FilmRemoteDataSource
import com.labi2d.challenge.moviestwo.domain.Film
import com.labi2d.challenge.moviestwo.domain.Error
import com.labi2d.challenge.moviestwo.domain.tryCall
import kotlinx.coroutines.flow.Flow

class FilmsRepository(
    private val localDataSource: FilmLocalDataSource,
    private val remoteDataSource: FilmRemoteDataSource
) {

    val films = localDataSource.films

    fun findByType(type: String): Flow<List<Film>> = localDataSource.findByType(type)

    suspend fun requestFilms(): Error? = tryCall {
        if (localDataSource.isEmpty()) {
            val films = remoteDataSource.fetchFilms()
            localDataSource.save(films)
        }
    }
}