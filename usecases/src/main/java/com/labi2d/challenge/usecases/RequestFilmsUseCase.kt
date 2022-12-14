package com.labi2d.challenge.usecases

import com.labi2d.challenge.data.FilmsRepository
import com.labi2d.challenge.domain.Error
import javax.inject.Inject

class RequestFilmsUseCase @Inject constructor(private val filmsRepository: FilmsRepository) {

    suspend operator fun invoke(): Error? {
        return filmsRepository.requestFilms()
    }
}