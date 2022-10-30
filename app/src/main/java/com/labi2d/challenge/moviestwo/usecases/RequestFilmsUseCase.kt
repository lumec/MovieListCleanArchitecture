package com.labi2d.challenge.moviestwo.usecases

import com.labi2d.challenge.moviestwo.data.Error
import com.labi2d.challenge.moviestwo.data.FilmsRepository

class RequestFilmsUseCase(private val filmsRepository: FilmsRepository) {

    suspend operator fun invoke(): Error? {
        return filmsRepository.requestFilms()
    }
}