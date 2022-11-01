package com.labi2d.challenge.moviestwo.di

import com.labi2d.challenge.data.FilmsRepository
import com.labi2d.challenge.usecases.FindFilmsUseCase
import com.labi2d.challenge.usecases.GetFilmsUseCases
import com.labi2d.challenge.usecases.RequestFilmsUseCase
import dagger.Module
import dagger.Provides

@Module
object UseCaseModule {

    @Provides
    fun provideFindFilmsUseCase(filmsRepository: FilmsRepository) =
        FindFilmsUseCase(filmsRepository)

    @Provides
    fun provideGetFilmsUseCases(filmsRepository: FilmsRepository) =
        GetFilmsUseCases(filmsRepository)

    @Provides
    fun provideRequestFilmsUseCase(filmsRepository: FilmsRepository) =
        RequestFilmsUseCase(filmsRepository)
}