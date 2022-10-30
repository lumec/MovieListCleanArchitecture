package com.labi2d.challenge.moviestwo.usecases

import com.labi2d.challenge.moviestwo.data.FilmsRepository

class GetFilmsUseCases(private val filmsRepository: FilmsRepository) {

    operator fun invoke() = filmsRepository.films
}