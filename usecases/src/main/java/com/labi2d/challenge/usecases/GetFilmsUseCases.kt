package com.labi2d.challenge.usecases

import com.labi2d.challenge.data.FilmsRepository

class GetFilmsUseCases(private val filmsRepository: FilmsRepository) {

    operator fun invoke() = filmsRepository.films
}