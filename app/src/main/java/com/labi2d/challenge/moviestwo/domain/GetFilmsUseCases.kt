package com.labi2d.challenge.moviestwo.domain

import com.labi2d.challenge.moviestwo.data.FilmsRepository

class GetFilmsUseCases(private val filmsRepository: FilmsRepository) {

    operator fun invoke() = filmsRepository.films
}