package com.labi2d.challenge.usecases

import com.labi2d.challenge.data.FilmsRepository
import com.labi2d.challenge.domain.Film
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindFilmsUseCase @Inject constructor(private val filmsRepository: FilmsRepository) {

    operator fun invoke(type: String): Flow<List<Film>> = filmsRepository.findByType(type)
}