package com.labi2d.challenge.data.datasource

import arrow.core.Either
import com.labi2d.challenge.domain.Error
import com.labi2d.challenge.domain.Film

interface FilmRemoteDataSource {

    suspend fun fetchFilms(): Either<Error, List<Film>>
}