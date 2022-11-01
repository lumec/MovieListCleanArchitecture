package com.labi2d.challenge.usecases

import com.labi2d.challenge.data.FilmsRepository
import javax.inject.Inject

class GetFilmsUseCases @Inject constructor(private val filmsRepository: FilmsRepository) {

    operator fun invoke() = filmsRepository.films
}