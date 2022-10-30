package com.labi2d.challenge.moviestwo.domain

import com.labi2d.challenge.moviestwo.data.FilmsRepository
import com.labi2d.challenge.moviestwo.data.database.Film
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.single

class FindFilmsUseCase(private val filmsRepository: FilmsRepository) {

    operator fun invoke(type: String): Flow<List<Film>> = filmsRepository.findByType(type)
}