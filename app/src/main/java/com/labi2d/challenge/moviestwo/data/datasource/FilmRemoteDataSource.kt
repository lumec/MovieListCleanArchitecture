package com.labi2d.challenge.moviestwo.data.datasource

import com.labi2d.challenge.moviestwo.domain.Film

interface FilmRemoteDataSource {
    suspend fun fetchFilms(): List<Film>
}