package com.labi2d.challenge.moviestwo.model.datasource

import com.labi2d.challenge.moviestwo.model.RemoteConnection

class FilmRemoteDataSource(
    private val apiKey: String,
) {
    suspend fun fetchFilms() = RemoteConnection.service.listFilms(apiKey)
}