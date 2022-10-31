package com.labi2d.challenge.moviestwo.framework.server

import arrow.core.Either
import com.labi2d.challenge.data.datasource.FilmRemoteDataSource
import com.labi2d.challenge.domain.Film
import com.labi2d.challenge.domain.Error
import com.labi2d.challenge.moviestwo.framework.tryCall

class FilmServerDataSource(
    private val apiKey: String,
) : FilmRemoteDataSource {
    override suspend fun fetchFilms(): Either<Error, List<Film>> = tryCall {
        RemoteConnection.service
            .listFilms(apiKey)
            .results
            .toDomainModel()
    }
}

private fun List<RemoteFilm>.toDomainModel(): List<Film> = map { it.toDomainModel() }

private fun RemoteFilm.toDomainModel(): Film = Film(
    id = 0,
    name ?: "",
    type?: ""
)