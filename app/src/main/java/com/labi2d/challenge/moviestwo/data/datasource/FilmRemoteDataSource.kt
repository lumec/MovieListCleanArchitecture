package com.labi2d.challenge.moviestwo.data.datasource

import com.labi2d.challenge.moviestwo.data.RemoteConnection

class FilmRemoteDataSource(
    private val apiKey: String,
) {
    suspend fun fetchFilms() = RemoteConnection.service.listFilms(apiKey)
}