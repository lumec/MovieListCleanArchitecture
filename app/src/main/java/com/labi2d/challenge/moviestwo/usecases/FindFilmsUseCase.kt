package com.labi2d.challenge.moviestwo.usecases

import com.labi2d.challenge.moviestwo.data.FilmsRepository
import com.labi2d.challenge.moviestwo.domain.Film
import kotlinx.coroutines.flow.Flow

class FindFilmsUseCase(private val filmsRepository: FilmsRepository) {

    operator fun invoke(type: String): Flow<List<Film>> = filmsRepository.findByType(type)
}