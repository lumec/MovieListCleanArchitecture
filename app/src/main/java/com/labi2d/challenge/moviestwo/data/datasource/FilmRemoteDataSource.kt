package com.labi2d.challenge.moviestwo.data.datasource

import com.labi2d.challenge.moviestwo.data.RemoteConnection
import com.labi2d.challenge.moviestwo.data.RemoteFilm
import com.labi2d.challenge.moviestwo.domain.Film

class FilmRemoteDataSource(
    private val apiKey: String,
) {
    suspend fun fetchFilms() = RemoteConnection.service.listFilms(apiKey).results.toDomainModel()
}

private fun List<RemoteFilm>.toDomainModel(): List<Film> = map { it.toDomainModel() }

private fun RemoteFilm.toDomainModel(): Film = Film(
    id = 0,
    name ?: "",
    type?: ""
)