package com.labi2d.challenge.moviestwo.data.datasource

import com.labi2d.challenge.moviestwo.domain.Film
import com.labi2d.challenge.moviestwo.data.database.FilmDao
import com.labi2d.challenge.moviestwo.data.database.Film as DbFilm
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FilmLocalDataSource(private val filmDao: FilmDao) {

    val films: Flow<List<Film>> = filmDao.getAll().map { it.toDomainModel() }

    fun findByType(type: String): Flow<List<Film>> =
        filmDao.findByType(type).map { it.toDomainModel() }

    suspend fun isEmpty(): Boolean = filmDao.countFilm() == 0

    suspend fun save(films: List<Film>) = filmDao.insertFilms(films.fromDomainModel())
}

private fun List<DbFilm>.toDomainModel(): List<Film> = map { it.toDomainModel() }

private fun DbFilm.toDomainModel(): Film = Film(
    id,
    name,
    type
)

private fun List<Film>.fromDomainModel(): List<DbFilm> = map { it.fromDomainModel() }

private fun Film.fromDomainModel(): DbFilm = DbFilm(
    id,
    name,
    type,
)