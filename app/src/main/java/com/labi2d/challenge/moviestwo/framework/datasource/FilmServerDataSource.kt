package com.labi2d.challenge.moviestwo.framework.datasource

import com.labi2d.challenge.moviestwo.data.RemoteConnection
import com.labi2d.challenge.moviestwo.data.RemoteFilm
import com.labi2d.challenge.moviestwo.data.datasource.FilmRemoteDataSource
import com.labi2d.challenge.moviestwo.domain.Film

class FilmServerDataSource(
    private val apiKey: String,
) : FilmRemoteDataSource {
    suspend fun fetchFilms() = RemoteConnection.service.listFilms(apiKey).results.toDomainModel()
}

private fun List<RemoteFilm>.toDomainModel(): List<Film> = map { it.toDomainModel() }

private fun RemoteFilm.toDomainModel(): Film = Film(
    id = 0,
    name ?: "",
    type?: ""
)