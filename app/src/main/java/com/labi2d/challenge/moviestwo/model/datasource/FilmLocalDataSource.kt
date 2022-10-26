package com.labi2d.challenge.moviestwo.model.datasource

import com.labi2d.challenge.moviestwo.model.database.Film
import com.labi2d.challenge.moviestwo.model.database.FilmDao
import kotlinx.coroutines.flow.Flow

class FilmLocalDataSource(private val filmDao: FilmDao) {

    val films: Flow<List<Film>> = filmDao.getAll()

    fun findByType(type: String): Flow<List<Film>> = filmDao.findByType(type)

    fun isEmpty(): Boolean = filmDao.countFilm() == 0

    fun hasFilmsByType(type: String): Boolean = filmDao.countFilmsByType(type) == 1

    fun save(films: List<Film>) = filmDao.insertFilms(films)
}