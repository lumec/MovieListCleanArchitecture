package com.labi2d.challenge.data.datasource

import com.labi2d.challenge.domain.Error
import com.labi2d.challenge.domain.Film
import kotlinx.coroutines.flow.Flow

interface FilmLocalDataSource {
    val films: Flow<List<Film>>

    fun findByType(type: String): Flow<List<Film>>
    suspend fun isEmpty(): Boolean
    suspend fun save(films: List<Film>): Error?
}