package com.labi2d.challenge.moviestwo.framework.database

import com.labi2d.challenge.data.datasource.FilmLocalDataSource
import com.labi2d.challenge.domain.Error
import com.labi2d.challenge.domain.Film
import com.labi2d.challenge.moviestwo.framework.tryCall
import com.labi2d.challenge.moviestwo.framework.database.Film as DbFilm
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FilmRoomDataSource @Inject constructor(private val filmDao: FilmDao) : FilmLocalDataSource {

    override val films: Flow<List<Film>> = filmDao.getAll().map { it.toDomainModel() }

    override fun findByType(type: String): Flow<List<Film>> =
        filmDao.findByType(type).map { it.toDomainModel() }

    override suspend fun isEmpty(): Boolean = filmDao.countFilm() == 0

    override suspend fun save(films: List<Film>): Error? = tryCall {
        filmDao.insertFilms(films.fromDomainModel())
    }.fold(
        ifLeft = { it },
        ifRight = { null }
    )
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