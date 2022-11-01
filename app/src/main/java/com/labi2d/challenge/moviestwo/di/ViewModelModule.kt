package com.labi2d.challenge.moviestwo.di

import com.labi2d.challenge.moviestwo.ui.home.HomeViewModelFactory
import com.labi2d.challenge.usecases.FindFilmsUseCase
import com.labi2d.challenge.usecases.GetFilmsUseCases
import com.labi2d.challenge.usecases.RequestFilmsUseCase
import dagger.Module
import dagger.Provides

@Module
object ViewModelModule {

    @Provides
    fun provideHomeViewModel(
        findFilmsUseCase: FindFilmsUseCase,
        getFilmsUseCases: GetFilmsUseCases,
        requestFilmsUseCase: RequestFilmsUseCase
    ):HomeViewModelFactory = HomeViewModelFactory(findFilmsUseCase, getFilmsUseCases, requestFilmsUseCase, "filmType")
}