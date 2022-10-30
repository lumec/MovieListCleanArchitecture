package com.labi2d.challenge.moviestwo.data.datasource

import com.labi2d.challenge.moviestwo.domain.Film
import kotlinx.coroutines.flow.Flow

interface FilmLocalDataSource {
    val films: Flow<List<Film>>

    fun findByType(type: String): Flow<List<Film>>
    suspend fun isEmpty(): Boolean
    suspend fun save(films: List<Film>)
}