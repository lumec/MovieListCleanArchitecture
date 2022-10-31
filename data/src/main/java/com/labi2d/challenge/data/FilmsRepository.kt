package com.labi2d.challenge.data

import com.labi2d.challenge.data.datasource.FilmLocalDataSource
import com.labi2d.challenge.data.datasource.FilmRemoteDataSource
import com.labi2d.challenge.domain.Error
import com.labi2d.challenge.domain.Film
import kotlinx.coroutines.flow.Flow

class FilmsRepository(
    private val localDataSource: FilmLocalDataSource,
    private val remoteDataSource: FilmRemoteDataSource
) {

    val films = localDataSource.films

    fun findByType(type: String): Flow<List<Film>> = localDataSource.findByType(type)

    suspend fun requestFilms(): Error? {
        if (localDataSource.isEmpty()) {
            val films = remoteDataSource.fetchFilms()
            films.fold(ifLeft = {return it}) {
                localDataSource.save(it)
            }
        }
        return null
    }
}